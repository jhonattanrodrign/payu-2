package pagos.payu.infrastructure.dto.antifraud;

import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pagos.payu.core.model.common.PaymentCard;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
@Builder
@AllArgsConstructor
public class AntifraudRequest {

	@NotNull
	private Integer orderId;
	@NotNull
	private UUID transactionId;
	@NotNull
	private BigDecimal value;
	@NotNull
	private Integer accountId;
	@NotNull
	private Integer merchantId;

	@Pattern(regexp = "MD5|SHA|SHA256|HMACSHA256")
	private String signatureAlgorithm;

	private PaymentCard paymentCard;
	public AntifraudRequest() {

	}
}
