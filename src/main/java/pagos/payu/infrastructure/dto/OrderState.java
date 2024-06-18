package pagos.payu.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {

    NEW("NEW"),

    READY("READY");

    private String value;
}
