package pl.ps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ps.model.api.ProductDto;
import pl.ps.model.api.ProductEntry;
import pl.ps.model.entity.ProductEntity;
import pl.ps.model.entity.UserEntity;
import pl.ps.model.repository.ProductRepository;
import pl.ps.model.repository.UserRepository;
import pl.ps.service.mapper.ProductMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Piotr Skowron
 */
@Service
@Transactional
public class ProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	@Autowired
	public ProductService(ProductRepository productRepository, UserRepository userRepository) {
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	public Set<ProductEntry> get(Long userId) {
		return productRepository.findAllByUserId(userId).stream()
				.map(ProductMapper::toEntry)
				.collect(Collectors.toSet());
	}

	public Long create(ProductDto dto) {
		UserEntity user = userRepository.getReferenceById(dto.userId());
		ProductEntity product = ProductMapper.toEntity(dto, user);
		productRepository.save(product);
		return product.getId();
	}

	public void update(Long id, ProductDto dto) {
		ProductEntity product = productRepository.getReferenceById(id);
		productRepository.save(ProductMapper.update(product, dto));
	}

	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
