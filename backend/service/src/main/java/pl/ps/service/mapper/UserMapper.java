package pl.ps.service.mapper;

import pl.ps.model.api.ProductEntry;
import pl.ps.model.api.UserDto;
import pl.ps.model.api.UserEntry;
import pl.ps.model.entity.UserEntity;

/**
 * @author Piotr Skowron
 */
public class UserMapper {

	public static UserEntry toEntry(UserEntity entity) {
		return UserEntry.builder()
				.id(entity.getId())
				.login(entity.getLogin())
				.balance(entity.getBalance())
				.build();
	}

	public static UserEntity toEntity(UserDto dto) {
		return UserEntity.builder()
				.login(dto.login())
				.password(dto.password())
				.balance(10000d)
				.build();
	}
}
