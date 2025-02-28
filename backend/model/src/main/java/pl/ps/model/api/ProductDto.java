package pl.ps.model.api;

import lombok.Builder;

/**
 * @author Piotr Skowron
 */
@Builder
public record ProductDto(
		String name,
		Long userId
) {
}
