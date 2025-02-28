package pl.ps.model.api;

import lombok.Builder;

/**
 * @author Piotr Skowron
 */
@Builder
public record CampaignDto(
		String name,
		String keywords,
		double bidAmount,
		double campaignFund,
		boolean status,
		Town town,
		int radius,
		Long productId
) {
}
