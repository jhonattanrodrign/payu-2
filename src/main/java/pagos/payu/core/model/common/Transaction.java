package pagos.payu.core.model.common;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "transaccion")
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
	@Column(name = "transaccion_id", unique = true, length = UUID_LENGTH, nullable = false)
	@Size(min = UUID_LENGTH, max = UUID_LENGTH)
	private String transactionId;

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
	@Column(name = "amounts", precision = 19, scale = 2)
	@NotNull
	public BigDecimal amounts;

	/**
	 * The main payment method. This PM is used to find the payment agreement in some cases
	 */
	@Column(name = "medio_pago_principal", length = ENUM_LENGTH_32, nullable = false)
	@NotNull
	private PaymentMethodMain paymentMethodMain;

	/**
	 * The payment method used in the transaction.
	 */
	@Column(name = "medio_pago", length = 32, nullable = false)
	public String paymentMethod;

	/**
	 * The payment network used in the transaction.
	 */
	@Column(name = "red_financiera_pagos", length = 32, nullable = false)
	public String paymentNetwork;

	/**
	 * The identifier of the payment agreement used in the transaction.
	 */
	@Column(name = "convenio_id", nullable = false)
	public Integer paymentAgreementId;
	/**
	 * The transaction response that contains the final state of the payment processor request.
	 */
	public TransactionResponse response;

	/**
	 * The credit card used with the transaction
	 */
	@Transient
	public CreditCard creditCard;

	/**
	 * The identifier of the credit card used with the transaction
	 */
	public String creditCardId;

	/**
	 * Exchange rate used to calculate the transaction amounts
	 */
	public BigDecimal exchangeRate;

	/**
	 * The response message that is received from the anti-fraud system.
	 */

	@Transient
	public AntiFraudPreValidationResponse preAntiFraudSystemResponseMessage;

	/**
	 * The parent transaction identifier (e.g. Authorization id for Capture or Capture id for Refund)
	 */
	public String parentTransactionId;

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


	/**
	 * Returns the parent transaction identifier
	 *
	 * @return The parent transaction identifier
	 */
	@Column(name = "transaccion_padre_id", length = UUID_LENGTH, nullable = false)
	@Size(min = UUID_LENGTH, max = UUID_LENGTH)
	public String getParentTransactionId() {

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
	@Column(name = "tipo", length = ENUM_LENGTH_32, nullable = false)
	@NotNull
	public String getTypeName() {

		return getTypeName();
	}

	/**
	 * Returns the transaction type
	 *
	 * @return The transaction type
	 */
	@Transient
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
	@Column(name = "tarjeta_credito_id", nullable = true)
	@Length(max = UUID_LENGTH)
	public String getCreditCardId() {

		return creditCardId;
	}


	/**
	 * Returns the exchange rate used to calculate the transaction amounts
	 *
	 * @return The exchange rate used to calculate the transaction amounts
	 */
	@Column(name = "tasa_cambio_pago")
	@DecimalMin(DECIMAL_VALUE_MIN)
	@DecimalMax(DECIMAL_VALUE_MAX)
	public BigDecimal getExchangeRate() {

		return exchangeRate;
	}
	/**
	 * {@inheritDoc}
	 */
	public void setTransactionId(final String transactionId) {

		this.transactionId = transactionId;
	}

}
