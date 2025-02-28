package pl.ps.model.api;

import lombok.Builder;

/**
 * @author Piotr Skowron
 */
@Builder
public record CampaignEntry(
		Long id,
		String name,
		String keywords,
		double bidAmount,
		double campaignFund,
		boolean status,
		Town town,
		int radius
) {
}
