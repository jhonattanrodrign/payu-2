package pagos.payu.infrastructure.dto.antifraud;

import java.math.BigDecimal;
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
	private String transactionId;
	@NotNull
	private BigDecimal value;
	@NotNull
	private Integer accountId;
	@NotNull
	private Integer merchantId;

	@Pattern(regexp = "MD5|SHA|SHA256|HMACSHA256")
	private String signatureAlgorithm;

	private PaymentCard paymentCard;

	public PaymentCard getPaymentCard() {

		return paymentCard;
	}

	public Integer getOrderId() {

		return orderId;
	}

	public String getTransactionId() {

		return transactionId;
	}

	public BigDecimal getValue() {

		return value;
	}

	public Integer getAccountId() {

		return accountId;
	}

	public Integer getMerchantId() {

		return merchantId;
	}

	public String getSignatureAlgorithm() {

		return signatureAlgorithm;
	}

	public AntifraudRequest() {

	}



	public AntifraudRequest(final Builder builder) {
		this.merchantId = builder.merchantId;
		this.accountId = builder.accountId;
		this.orderId = builder.orderId;
		this.transactionId = builder.transactionId;
		this.value = builder.value;
		this.signatureAlgorithm = builder.signatureAlgorithm;
		this.paymentCard = builder.paymentCard;

	}


	public static class Builder {

		private Integer orderId;

		private String transactionId;

		private BigDecimal value;

		private Integer accountId;

		private Integer merchantId;

		private String signatureAlgorithm;

		private PaymentCard paymentCard;

		public Integer getOrderId() {

			return orderId;
		}

		public String getTransactionId() {

			return transactionId;
		}

		public BigDecimal getValue() {

			return value;
		}

		public Integer getAccountId() {

			return accountId;
		}

		public Integer getMerchantId() {

			return merchantId;
		}

		public String getSignatureAlgorithm() {

			return signatureAlgorithm;
		}

		public PaymentCard getPaymentCard() {

			return paymentCard;
		}


		public Builder withOrderId(final Integer orderId) {

			this.orderId = orderId;
			return this;
		}

		public Builder withTransactionId(final String transactionId) {

			this.transactionId = transactionId;
			return this;
		}

		public Builder withAccountId(final Integer accountId) {

			this.accountId = accountId;
			return this;
		}

		public Builder withMerchantId(final Integer merchantId) {

			this.merchantId = merchantId;
			return this;
		}

		public Builder withValue(final BigDecimal value) {

			this.value = value;
			return this;
		}

		public Builder withSignatureAlgorithm(final String signatureAlgorithm) {

			this.signatureAlgorithm = signatureAlgorithm;
			return this;
		}

		public Builder withPaymentCard(final PaymentCard paymentCard) {

			this.paymentCard = paymentCard;
			return this;
		}

		public AntifraudRequest build() {

			return new AntifraudRequest(this);
		}
	}
}
