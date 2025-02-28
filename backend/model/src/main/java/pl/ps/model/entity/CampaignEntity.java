package pl.ps.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.ps.model.api.Town;

/**
 * @author Piotr Skowron
 */
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CampaignEntity extends AbstractEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String keywords;

	@Column(nullable = false)
	private double bidAmount;

	@Column(nullable = false)
	private double campaignFund;

	@Column(nullable = false)
	private boolean status;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Town town;

	@Column(nullable = false)
	private int radius;

	@ToString.Exclude
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private ProductEntity product;

	@Builder(toBuilder = true)
	public CampaignEntity(Long id, String name, String keywords, double bidAmount, double campaignFund, boolean status, Town town,
						  int radius, ProductEntity product) {
		super(id);
		this.name = name;
		this.keywords = keywords;
		this.bidAmount = bidAmount;
		this.campaignFund = campaignFund;
		this.status = status;
		this.town = town;
		this.radius = radius;
		this.product = product;
	}
}
