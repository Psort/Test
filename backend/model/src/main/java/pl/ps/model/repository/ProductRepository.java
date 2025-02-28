package pl.ps.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ps.model.entity.CampaignEntity;
import pl.ps.model.entity.ProductEntity;

import java.util.Set;

/**
 * @author Piotr Skowron
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	ProductEntity findByName(String name);
	Set<ProductEntity> findAllByUserId(Long user);
}
