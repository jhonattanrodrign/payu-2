package pagos.payu.core.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
@Builder
@AllArgsConstructor
public class AmountDetail {

	/**
	 * The tax value of the payment request. If the field is empty, the system assumes to be zero (All countries except
	 * for Colombia) or the 16% the total value (For Colombia)
	 */
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal tax;

	private String currency;

	/**
	 * Private default constructor required by Jackson
	 */
	private AmountDetail() {

		super();
	}
}
