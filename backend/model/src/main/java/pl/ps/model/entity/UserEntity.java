package pl.ps.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Piotr Skowron
 */
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends AbstractEntity {

	@Column(nullable = false)
	private String login;

	@Column(nullable = false)
	private String password;

	private Double balance;

	@Builder(toBuilder = true)
	public UserEntity(Long id, String login, String password, Double balance) {
		super(id);
		this.login = login;
		this.password = password;
		this.balance = balance;
	}

	public void subtractFromBalance(Double amount) {
		this.balance = balance - amount;
	}

}
