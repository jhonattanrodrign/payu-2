package pagos.payu.infrastructure.dto.capture;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pagos.payu.core.model.common.AbstractResponse;
import pagos.payu.core.model.common.TransactionResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
@Data
@AllArgsConstructor
public class CaptureResponse extends AbstractResponse {


	/**
	 * The traceability code related with the response
	 */
	private String traceabilityCode;

	/**
	 * The authorization code
	 */
	private String authorizationCode;

	/**
	 * Creates the {@link CaptureResponse} from the {@link TransactionResponse} entity.
	 *
	 * @param transactionResponse the {@link TransactionResponse} base entity
	 */
	public CaptureResponse(final TransactionResponse transactionResponse) {

		super(transactionResponse);
	}
}
