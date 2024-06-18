package pagos.payu.core.model.common;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "orden")
@DynamicInsert
@DynamicUpdate
public class Order {

	private Integer accountId;
	private Integer merchantId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private String description;
	private BigDecimal amount;
	private Date creationDate;
	private String state;
	private String status;

	@Column(name = "cuenta_id", nullable = false)
	public Integer getAccountId() {

		return accountId;
	}

	public void setAccountId(final Integer accountId) {

		this.accountId = accountId;
	}

	@Column(name = "usuario_id", nullable = false)
	public Integer getMerchantId() {

		return merchantId;
	}

	public void setMerchantId(final Integer merchantId) {

		this.merchantId = merchantId;
	}

	@Id
	@Column(name = "orden_id", nullable = false)
	public Integer getOrderId() {

		return orderId;
	}

	public void setOrderId(final Integer orderId) {

		this.orderId = orderId;
	}

	@Column(name = "descripcion")
	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public BigDecimal getAmount() {

		return amount;
	}

	public void setAmount(final BigDecimal amount) {

		this.amount = amount;
	}

	@Column(name = "fecha_creacion")
	public Date getCreationDate() {

		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {

		this.creationDate = creationDate;
	}

	@Column(name = "estado", nullable = false)
	public String getState() {

		return state;
	}

	public void setState(final String state) {

		this.state = state;
	}

	@Column(name = "status", nullable = false)
	public String getStatus() {

		return status;
	}

	public void setStatus(final String status) {

		this.status = status;
	}
}
