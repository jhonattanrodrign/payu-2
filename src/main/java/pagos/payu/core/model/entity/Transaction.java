package pagos.payu.core.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import pagos.payu.core.model.common.Constants;
import pagos.payu.core.model.common.TransactionType;
import pagos.payu.core.model.common.TransactionWorkflowStatus;

@Entity
@Table(name = "purchase_transaction")
@DynamicInsert
@DynamicUpdate
@Data
public class Transaction {

	/**
	 * The class UID version.
	 */
	private static final long serialVersionUID = 1L;
	private static final int ENUM_LENGTH_32 = 32;
	private static final int UUID_LENGTH = 32;
	private static final String DECIMAL_VALUE_MIN = "0.00";
	private static final String DECIMAL_VALUE_MAX = "999999999999999.9";

	/**
	 * The transaction identifier.
	 */
	@Id
	@Column(name = "transaccion_id", unique = true, nullable = false,columnDefinition = "uuid")
	//@Column(name = "transaccion_id", unique = true, nullable = false)
	private UUID transactionId;

	/**
	 * The identifier of associated the merchant
	 */
	private Integer merchantId;

	/**
	 * The transaction type
	 */
	private TransactionType type;

	/**
	 * The transaction workflow status that contains the last executed step on the payment workflow.
	 */
	private TransactionWorkflowStatus status;

	/**
	 * The creation date
	 */
	@Column(name = "fecha_creacion", nullable = false)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	/**
	 * Flag that indicated if the transaction is a payment attempt (Authorization/Capture)
	 */
	@Column(name = "es_intento_pago", nullable = false)
	@NotNull
	private Boolean isPaymentAttempt;

 	/**
	 * Transaction amounts
	 */
	//@Column(name = "amounts", precision = 19, scale = 2)
	//@NotNull
	//@Valid
	//@OneToOne(cascade = CascadeType.ALL)
	//	@JoinColumn(name = "amount_id", referencedColumnName = "amountId")
	//public Amount amount;

	private String currency;

	@NotNull
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	@JsonProperty("value")
	private BigDecimal total;

	/**
	 * The payment method used in the transaction.
	 */
	@Column(name = "medio_pago", length = 32, nullable = false)
	public String paymentMethod;


	/**
	 * The credit card used with the transaction
	 */
	//@Transient
	//public CreditCard creditCard;

	/**
	 * The identifier of the credit card used with the transaction
	 */
	public UUID creditCardId;

	/**
	 * The response message that is received from the anti-fraud system.
	 */

	//@Transient
	//public AntiFraudPreValidationResponse preAntiFraudSystemResponseMessage;

	/**
	 * The parent transaction identifier (e.g. Authorization id for Capture or Capture id for Refund)
	 */
	public UUID parentTransactionId;

	/**
	 * The expiration date
	 */
	@Column(name = "fecha_expiracion", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	public Date expirationDate;

	/**
	 * The last updated date
	 */
	@Column(name = "fecha_ultima_actualizacion")
	public Date lastUpdatedDate;

	/**
	 * The identifier of the order related to the transaction
	 */
	@Column(name = "orden_id", nullable = false)
	@NotNull
	private Integer orderId;


	private String paymentMethodMainName;

	private String fraudStatus;

	private String paymentNetworkResponseCode;

	private String responseCode;

	private String traceabilityCode;


	/**
	 * Returns the parent transaction identifier
	 *
	 * @return The parent transaction identifier
	 */
	@Column(name = "transaccion_padre_id", nullable = false)
	//@Size(min = UUID_LENGTH, max = UUID_LENGTH)
	public UUID getParentTransactionId() {

		return parentTransactionId;
	}


	/**
	 * Returns the merchant identifier
	 *
	 * @return The merchant identifier
	 */
	@Column(name = "usuario_id", nullable = false)
	@NotNull
	public Integer getMerchantId() {

		return merchantId;
	}

	/**
	 * Returns the transaction type
	 *
	 * @return The transaction type
	 */
	//@Column(name = "tipo", length = ENUM_LENGTH_32, nullable = false)
	//@NotNull
	//public String getTypeName() {

	//	return getTypeName();
	//}

	/**
	 * Returns the transaction type
	 *
	 * @return The transaction type
	 */
	//@Transient
	@Column(name = "tipo", nullable = false)
	public TransactionType getType() {

		return type;
	}


	/**
	 * Returns the transaction workflow status
	 *
	 * @return the transaction workflow status
	 */
	@Transient
	public TransactionWorkflowStatus getStatus() {

		return status;
	}


	/**
	 * Sets the credit card identifier
	 *
	 * @return The credit card identifier
	 */
	//@Column(name = "tarjeta_credito_id", nullable = true)
	//@Length(max = UUID_LENGTH)
	//public String getCreditCardId() {

	//	return creditCardId;
	//}


	/**
	 * Returns the exchange rate used to calculate the transaction amounts
	 *
	 * @return The exchange rate used to calculate the transaction amounts
	 */
	//@Column(name = "tasa_cambio_pago")
	//@DecimalMin(DECIMAL_VALUE_MIN)
	//@DecimalMax(DECIMAL_VALUE_MAX)
	//public BigDecimal getExchangeRate() {

	//	return exchangeRate;
	//}
	/**
	 * {@inheritDoc}
	 */
	public void setTransactionId(final UUID transactionId) {

		this.transactionId = transactionId;
	}

}
