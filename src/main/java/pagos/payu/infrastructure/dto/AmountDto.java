package pagos.payu.infrastructure.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmountDto {

    @NotNull
    private BigDecimal value;

    @NotNull
    private String currency;

    @Valid
    private List<TaxDto> taxes;
}