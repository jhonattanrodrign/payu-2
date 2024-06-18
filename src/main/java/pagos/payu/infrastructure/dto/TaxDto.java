package pagos.payu.infrastructure.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxDto {

    @NotNull
    private BigDecimal value;

    @NotNull
    private String currency;
}
