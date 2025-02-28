package pl.ps.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Piotr Skowron
 */
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity extends AbstractEntity {

	@Column(nullable = false)
	private String name;

	@ToString.Exclude
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UserEntity user;

	@Builder(toBuilder = true)
	public ProductEntity(Long id, String name, UserEntity user) {
		super(id);
		this.name = name;
		this.user = user;
	}
}
