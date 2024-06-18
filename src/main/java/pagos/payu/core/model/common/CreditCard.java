package pagos.payu.core.model.common;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tarjeta_credito")
public class CreditCard {

	private static final long serialVersionUID = 1L;
	private static final int UUID_LENGTH = 36;
	private static final int ENUM_LENGTH_64 = 64;
	private static final int ENUM_LENGTH_32 = 32;

	private CardholderData cardholderData;

	/**
	 * The credit card state
	 */
	private String state;

	/**
	 * The creation date
	 */
	private Date creationDate;

	/**
	 * Country of expedition
	 */
	private String country;

	/**
	 * The financial institution that issues cards to consumers on behalf of the card networks (Visa, MasterCard, etc).
	 * The issuing bank is also known as the credit or debit card company.
	 */
	private String issuerBank;

	/**
	 * Flag that indicates if the request must be processed without security code verification. This is useful for cases
	 * like recurring payments, where only the number and expiration date is stored.
	 */
	private Boolean  processWithoutCvv2;

	/**
	 * The credit card identifier.
	 */
	private String id;

	/**
	 * The identifier of the order related to the credit card
	 */
	private Integer orderId;

	public static long getSerialVersionUID() {

		return serialVersionUID;
	}

	@Embedded
	@Valid
	public CardholderData getCardHolderData() {

		return cardholderData;
	}

	public void setCardHolderData(final CardholderData cardholderData) {

		this.cardholderData = cardholderData;
	}

	@Column(name = "state", length = ENUM_LENGTH_32, nullable = false)
	@NotNull
	public String getState() {

		return state;
	}

	public void setState(final String state) {

		this.state = state;
	}

	@Column(name = "fecha_creacion", nullable = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {

		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {

		this.creationDate = creationDate;
	}

	@Column(name = "pais_iso_3166", nullable = true, length = 3)
	@Length(min = 2, max = 3)
	public String getCountry() {

		return country;
	}

	public void setCountry(final String country) {

		this.country = country;
	}

	@Column(name = "banco_emisor", length = 255, nullable = true)
	@Length(max = 255)
	public String getIssuerBank() {

		return issuerBank;
	}

	public void setIssuerBank(final String issuerBank) {

		this.issuerBank = issuerBank;
	}

	@Column(name = "procesar_sin_cvv2")
	public Boolean getProcessWithoutCvv2() {

		return processWithoutCvv2;
	}

	public void setProcessWithoutCvv2(final Boolean processWithoutCvv2) {

		this.processWithoutCvv2 = processWithoutCvv2;
	}

	@Id
	@Column(name = "tarjeta_credito_id", unique = true, length = UUID_LENGTH, nullable = false)
	@Size(min = UUID_LENGTH, max = UUID_LENGTH)
	//@SpaceId(autoGenerate = false)
	public String getId() {

		return id;
	}

	public void setId(final String id) {

		this.id = id;
	}

	@Column(name = "orden_id", nullable = false)
	@NotNull
	public Integer getOrderId() {

		return orderId;
	}

	public void setOrderId(final Integer orderId) {

		this.orderId = orderId;
	}

}
