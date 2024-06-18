package pagos.payu.core.model.common;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import lombok.Builder;

@Builder
public class AntiFraudPreValidationResponse {

	/**
	 * Default class serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * PayU Internal error code
	 */
	public static final String INTERNAL_ERROR_CODE = "1000";
	private static final int UUID_LENGTH = 32;

	private String providerAntiFraud;

	private String codeResponse;
	private String errorCode;
	private String errorMessage;
	private String decision;
	private String processingTime;
	private String neuralScore;

	/**
	 * The transaction identifier.
	 */
	private String id;

	/**
	 * The identifier of the order related to the transaction
	 */
	private Integer orderId;

	/**
	 * The credit card issuer bank
	 */
	private String creditCardIssuerBank;

	/**
	 * The credit card country
	 */
	private String creditCardCountry;

	/**
	 * Must return the state of the antifraud validation
	 */
	private AntiFraudValidationState antifraudValidationState;


	@Column(name = "proveedor_antifraude", length = 8)
	public String getProviderAntiFraud() {

		return providerAntiFraud;
	}

	public void setProviderAntiFraud(final String providerAntiFraud) {

		this.providerAntiFraud = providerAntiFraud;
	}

	@Column(name = "pre_maf_respuesta_codigo")
	public String getCodeResponse() {

		return codeResponse;
	}

	public void setCodeResponse(final String codeResponse) {

		this.codeResponse = codeResponse;
	}

	@Column(name = "pre_maf_respuesta_codigo_error")
	public String getErrorCode() {

		return errorCode;
	}

	public void setErrorCode(final String errorCode) {

		this.errorCode = errorCode;
	}

	@Column(name = "pre_maf_respuesta_mensaje_error", length = 1024)
	@Length(max = 1024)
	public String getErrorMessage() {

		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {

		this.errorMessage = errorMessage;
	}

	@Column(name = "pre_maf_respuesta_decision")
	public String getDecision() {

		return decision;
	}

	public void setDecision(final String decision) {

		this.decision = decision;
	}

	@Column(name = "pre_maf_respuesta_tiempo_procesamiento")
	public String getProcessingTime() {

		return processingTime;
	}

	public void setProcessingTime(final String processingTime) {

		this.processingTime = processingTime;
	}

	@Column(name = "pre_maf_respuesta_puntaje_rn")
	public String getNeuralScore() {

		return neuralScore;
	}

	public void setNeuralScore(final String neuralScore) {

		this.neuralScore = neuralScore;
	}

	@Id
	@Column(name = "transaccion_id",
			unique = true,
			length = UUID_LENGTH,
			nullable = false,
			updatable = false,
			insertable = false)
	@Size(min = UUID_LENGTH, max = UUID_LENGTH)
	public String getId() {

		return id;
	}

	public void setId(final String id) {

		this.id = id;
	}

	@Column(name = "orden_id", nullable = false, insertable = false, updatable = false)
	@NotNull
	public Integer getOrderId() {

		return orderId;
	}

	public void setOrderId(final Integer orderId) {

		this.orderId = orderId;
	}

	public String getCreditCardIssuerBank() {

		return creditCardIssuerBank;
	}

	public void setCreditCardIssuerBank(final String creditCardIssuerBank) {

		this.creditCardIssuerBank = creditCardIssuerBank;
	}

	@Transient
	public String getCreditCardCountry() {

		return creditCardCountry;
	}

	public void setCreditCardCountry(final String creditCardCountry) {

		this.creditCardCountry = creditCardCountry;
	}

	@Transient
	public AntiFraudValidationState getAntifraudValidationState() {

		return antifraudValidationState;
	}

	public void setAntifraudValidationState(final AntiFraudValidationState antifraudValidationState) {

		this.antifraudValidationState = antifraudValidationState;
	}
}
