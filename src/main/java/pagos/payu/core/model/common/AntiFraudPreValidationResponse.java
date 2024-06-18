package pagos.payu.core.model.common;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import lombok.Builder;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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

	/**
	 * The transaction identifier.
	 */
	private UUID id;

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
}
