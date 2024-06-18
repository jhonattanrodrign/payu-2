package pagos.payu.infrastructure.dto.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pagos.payu.core.model.common.AbstractResponse;
import pagos.payu.core.model.common.TransactionResponse;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
@AllArgsConstructor
@Data
public class AuthorizationResponse extends AbstractResponse {

	/**
	 * The traceability code related with the response
	 */
	private String traceabilityCode;

	/**
	 * The authorization code
	 */
	private String authorizationCode;

	/**
	 * Payments Response default constructor, required by Jackson
	 */
	private AuthorizationResponse() {

		super();
	}

	/**
	 * Payment response constructor using a {@link TransactionResponse}.
	 *
	 * @param transactionResponse The {@link TransactionResponse} entity
	 */
	public AuthorizationResponse(final TransactionResponse transactionResponse) {

		super(transactionResponse);
		authorizationCode = transactionResponse.getAuthorizationCode();
		traceabilityCode = transactionResponse.getTraceabilityCode();
	}

	/**
	 * Returns the traceabilityCode
	 *
	 * @return the traceabilityCode
	 */
	public String getTraceabilityCode() {

		return traceabilityCode;
	}

	/**
	 * Returns the authorizationCode
	 *
	 * @return the authorizationCode
	 */
	public String getAuthorizationCode() {

		return authorizationCode;
	}

}
