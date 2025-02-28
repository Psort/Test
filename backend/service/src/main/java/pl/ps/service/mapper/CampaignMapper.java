package pl.ps.service.mapper;

import pl.ps.model.api.CampaignDto;
import pl.ps.model.api.CampaignEntry;
import pl.ps.model.entity.CampaignEntity;
import pl.ps.model.entity.ProductEntity;

/**
 * @author Piotr Skowron
 */
public class CampaignMapper {

	public static CampaignEntry toEntry(CampaignEntity entity) {
		return CampaignEntry.builder()
				.id(entity.getId())
				.name(entity.getName())
				.keywords(entity.getKeywords())
				.bidAmount(entity.getBidAmount())
				.campaignFund(entity.getCampaignFund())
				.status(entity.isStatus())
				.town(entity.getTown())
				.radius(entity.getRadius())
				.build();
	}

	public static CampaignEntity toEntity(CampaignDto dto, ProductEntity product) {
		return buildCampaignEntity(null, dto, product);
	}

	public static CampaignEntity update(CampaignEntity entity, CampaignDto dto, ProductEntity product) {
		return buildCampaignEntity(entity, dto, product);
	}

	private static CampaignEntity buildCampaignEntity(CampaignEntity entity, CampaignDto dto, ProductEntity product) {
		CampaignEntity.CampaignEntityBuilder builder = (entity == null) ? CampaignEntity.builder() : entity.toBuilder();
		return builder
				.name(dto.name())
				.keywords(dto.keywords())
				.bidAmount(dto.bidAmount())
				.campaignFund(dto.campaignFund())
				.status(dto.status())
				.town(dto.town())
				.radius(dto.radius())
				.product(product)
				.build();
	}

}
