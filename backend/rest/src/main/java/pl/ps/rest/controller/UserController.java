package pl.ps.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ps.model.api.UserDto;
import pl.ps.model.api.UserEntry;
import pl.ps.service.UserService;

/**
 * @author Piotr Skowron
 */
@RestController
@RequestMapping(path = "/users", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private final UserService service;

	@Autowired
	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping("/login")
	public UserEntry login(@RequestBody UserDto dto) {
		return service.login(dto);
	}

	@PostMapping("/create")
	public UserEntry creteUser(@RequestBody UserDto dto) {
		return service.create(dto);
	}
}
