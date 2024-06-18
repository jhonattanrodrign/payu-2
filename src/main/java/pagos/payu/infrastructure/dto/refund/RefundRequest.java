package pagos.payu.infrastructure.dto.refund;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import pagos.payu.core.model.common.Constants;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
@Builder
public class RefundRequest {

	/**
	 * Identifier of the authorized order to refund
	 */
	@NotNull
	@Min(0)
	private Integer orderId;

	/**
	 * The payment request value
	 */
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal value;

	/**
	 * Identifier of the capture transaction to refund
	 */
	@NotBlank
	private String captureId;

	/**
	 * Returns the orderId
	 *
	 * @return the orderId
	 */
	public Integer getOrderId() {

		return orderId;
	}

	/**
	 * Sets the orderId
	 *
	 * @param orderId the orderId to set
	 */
	public void setOrderId(final Integer orderId) {

		this.orderId = orderId;
	}

	/**
	 * Returns the value
	 *
	 * @return the value
	 */
	public BigDecimal getValue() {

		return value;
	}

	/**
	 * Sets the value
	 *
	 * @param value the value to set
	 */
	public void setValue(final BigDecimal value) {

		this.value = value;
	}

	public String getCaptureId() {

		return captureId;
	}

	public void setCaptureId(String captureId) {

		this.captureId = captureId;
	}

}
