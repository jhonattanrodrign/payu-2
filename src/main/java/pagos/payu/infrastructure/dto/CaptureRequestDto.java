package pagos.payu.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pagos.payu.core.model.common.Constants;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptureRequestDto implements Serializable {


    private static final long serialVersionUID = 1116011147590715688L;

    @NotNull
    private Integer orderId;

    /**
     * Identifier of the authorized transaction to capture
     */
    @NotNull
    private String authorizationId;

    /**
     * The payment request value
     */
    @DecimalMin(Constants.DECIMAL_VALUE_MIN)
    @DecimalMax(Constants.DECIMAL_VALUE_MAX)
    private BigDecimal value;
}
