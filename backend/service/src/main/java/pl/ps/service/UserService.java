package pl.ps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ps.model.api.ProductDto;
import pl.ps.model.api.UserDto;
import pl.ps.model.api.UserEntry;
import pl.ps.model.entity.UserEntity;
import pl.ps.model.repository.UserRepository;
import pl.ps.service.mapper.UserMapper;

/**
 * @author Piotr Skowron
 */
@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserEntry create(UserDto dto) {
		UserEntity user = UserMapper.toEntity(dto);
		userRepository.save(user);
		return UserMapper.toEntry(user);
	}

	public UserEntry login(UserDto dto) {
		UserEntity user = userRepository.findByLogin(dto.login());
		if (user != null && dto.password().equals(user.getPassword())) {
			return UserMapper.toEntry(user);
		}
		return null;
	}
}
