package pl.ps.model.api;

import lombok.Builder;

/**
 * @author Piotr Skowron
 */
@Builder
public record UserEntry(
		Long id,
		String login,
		Double balance
) {
}
