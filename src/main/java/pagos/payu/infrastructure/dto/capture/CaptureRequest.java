package pagos.payu.infrastructure.dto.capture;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pagos.payu.core.model.common.Constants;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CaptureRequest {

	/**
	 * Identifier of the authorized order to capture
	 */
	@NotNull
	@Min(0)
	private Integer orderId;

	/**
	 * Identifier of the authorized transaction to capture
	 */
	@NotNull
	private String authorizationId;

	private String currency;

	/**
	 * The payment request value
	 */
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal value;

	/**
	 * Returns the orderId
	 *
	 * @return the orderId
	 */
	public Integer getOrderId() {

		return orderId;
	}

	/**
	 * Returns the authorization Id
	 *
	 * @return the Authorization Id
	 */
	public String getAuthorizationId() {

		return authorizationId;
	}

	/**
	 * Returns the value
	 *
	 * @return the value
	 */
	public BigDecimal getValue() {

		return value;
	}
}
