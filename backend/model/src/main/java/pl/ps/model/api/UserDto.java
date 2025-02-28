package pl.ps.model.api;

import lombok.Builder;

/**
 * @author Piotr Skowron
 */
@Builder
public record UserDto(
		String login,
		String password
) {
}
