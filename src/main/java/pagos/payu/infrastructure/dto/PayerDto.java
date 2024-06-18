package pagos.payu.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayerDto {
    @NotBlank
    private String merchantPayerId;

    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    private String emailAddress;

    @NotBlank
    private String contactPhone;

    @NotBlank
    private String dniNumber;

    private String dniType;
}
