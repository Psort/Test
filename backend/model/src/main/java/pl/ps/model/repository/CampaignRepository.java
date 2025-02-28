package pl.ps.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ps.model.entity.CampaignEntity;

import java.util.Set;

/**
 * @author Piotr Skowron
 */
public interface CampaignRepository extends JpaRepository<CampaignEntity, Long> {
	CampaignEntity findByName(String name);
	Set<CampaignEntity> findAllByProductId(Long productId);
}
