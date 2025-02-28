package pl.ps.service.mapper;

import pl.ps.model.api.ProductDto;
import pl.ps.model.api.ProductEntry;
import pl.ps.model.entity.ProductEntity;
import pl.ps.model.entity.UserEntity;

/**
 * @author Piotr Skowron
 */
public class ProductMapper {

	public static ProductEntry toEntry(ProductEntity entity) {
		return ProductEntry.builder()
				.id(entity.getId())
				.name(entity.getName())
				.build();
	}

	public static ProductEntity toEntity(ProductDto dto, UserEntity user) {
		return ProductEntity.builder()
				.name(dto.name())
				.user(user)
				.build();
	}

	public static ProductEntity update(ProductEntity entity, ProductDto dto) {
		return entity.toBuilder()
				.name(dto.name())
				.build();
	}
}
