package pl.ps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ps.model.api.CampaignDto;
import pl.ps.model.api.CampaignEntry;
import pl.ps.model.entity.CampaignEntity;
import pl.ps.model.entity.ProductEntity;
import pl.ps.model.entity.UserEntity;
import pl.ps.model.repository.CampaignRepository;
import pl.ps.model.repository.ProductRepository;
import pl.ps.model.repository.UserRepository;
import pl.ps.service.mapper.CampaignMapper;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Piotr Skowron
 */
@Service
@Transactional
public class CampaignService {

	private final CampaignRepository campaignRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	@Autowired
	public CampaignService(CampaignRepository campaignRepository1, ProductRepository productRepository, UserRepository userRepository) {
		this.campaignRepository = campaignRepository1;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	public Set<CampaignEntry> get(Long productId) {
		return campaignRepository.findAllByProductId(productId).stream()
				.map(CampaignMapper::toEntry)
				.collect(Collectors.toSet());
	}

	public Long create(CampaignDto dto) {
		ProductEntity product = productRepository.getReferenceById(dto.productId());
		UserEntity user = product.getUser();
		CampaignEntity campaign = CampaignMapper.toEntity(dto, product);

		if (campaign.getCampaignFund() > user.getBalance()) {
			return null;
		}
		user.subtractFromBalance(campaign.getCampaignFund());
		campaignRepository.save(campaign);
		userRepository.save(user);
		return campaign.getId();
	}

	public void delete(Long id) {
		campaignRepository.deleteById(id);
	}

	public void update(Long id, CampaignDto dto) {
		ProductEntity product = productRepository.getReferenceById(dto.productId());
		CampaignEntity campaign = campaignRepository.getReferenceById(id);
		campaignRepository.save(CampaignMapper.update(campaign, dto, product));
	}
}
