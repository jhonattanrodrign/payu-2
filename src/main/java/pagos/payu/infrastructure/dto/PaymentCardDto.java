package pagos.payu.infrastructure.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCardDto {

    @NotBlank
    private String number;

    @NotBlank
    private String securityCode;

    @NotBlank
    private String name; // Este campo corresponde al nombre del titular de la tarjeta

    @NotBlank
    private String expirationDate; // Formato "YYYY-MM" según el JSON proporcionado
}
