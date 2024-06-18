package pagos.payu.infrastructure.dto.refund;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pagos.payu.core.model.common.AbstractResponse;
import pagos.payu.core.model.common.TransactionResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
public class RefundResponse extends AbstractResponse {

	/**
	 * The traceability code related with the response
	 */
	private String traceabilityCode;

	/**
	 * The capture code
	 */
	private String captureCode;

	/**
	 * Creates the {@link RefundResponse} from the {@link TransactionResponse} entity.
	 *
	 * @param transactionResponse the {@link TransactionResponse} base entity
	 */
	public RefundResponse(final TransactionResponse transactionResponse) {

		super(transactionResponse);
	}
}
