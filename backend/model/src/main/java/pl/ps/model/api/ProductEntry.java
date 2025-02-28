package pl.ps.model.api;

import lombok.Builder;

/**
 * @author Piotr Skowron
 */
@Builder
public record ProductEntry(
		Long id,
		String name
) {
}
