package pagos.payu.core.model.common;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class Amount {

	/**
	 * Currency of the given amounts. The field must be in ISO4212 code (3 letters).
	 * <p>
	 * Allowed values = { "ARS", "BRL", "CLP", "COP", "MXN", "PEN", "USD" }
	 */
	private String currency;

	/**
	 * The payment request total
	 */
	@NotNull
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal total;

	/**
	 * Amount detail
	 */
	@Valid
	private AmountDetail detail;

	/**
	 * Private default constructor required by Jackson
	 */
	private Amount() {

		super();
	}

	/**
	 * Amounts constructor from builder
	 *
	 * @param builder Amounts builder
	 */
	public Amount(final Builder builder) {

		this.currency = builder.currency;
		this.total = builder.value;
		this.detail = builder.detail;

	}

	/**
	 * Returns the currency
	 *
	 * @return the currency
	 */
	@NotNull
	public String getCurrency() {

		return currency;
	}

	/**
	 * Sets the currency
	 *
	 * @param currency the currency to set
	 */
	public void setCurrency(final String currency) {

		this.currency = currency;
	}

	/**
	 * Returns the total
	 *
	 * @return the total
	 */

	public BigDecimal getTotal() {

		return total;
	}

	/**
	 * Sets the total
	 *
	 * @param total the total to set
	 */
	public void setTotal(final BigDecimal total) {

		this.total = total;
	}

	/**
	 * Returns the amount detail
	 *
	 * @return The amount detail
	 */
	public AmountDetail getDetail() {

		return detail;
	}

	/**
	 * Sets the amount detail
	 *
	 * @param detail The amount detail to set
	 */
	public void setDetail(AmountDetail detail) {

		this.detail = detail;
	}

	/**
	 * Returns a string representation of the amounts, creating a "textually represents" of all the attributes.
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {

		return "Amounts{" +
				"currency=" + currency +
				", total=" + total +
				", detail=[" + detail + "]}";
	}

	/**
	 * Creates a {@linkplain Builder Amounts Builder} with the initial currency and total
	 *
	 * @param currency The currency to set
	 * @param value The total to set
	 * @return A new {@linkplain Builder Amounts Builder} instance
	 */
	public static Builder of(final String currency, final BigDecimal value) {

		return new Builder().withCurrency(currency).withTotalValue(value);
	}

	/**
	 * Builder class for {@linkplain Amount} entities
	 *
	 * @author Manuel E. Vieda (manuel.vieda@payulatam.com)
	 */
	public static class Builder {

		/**
		 * Currency of the given amounts. The field must be in ISO4212 code (3 letters).
		 */
		private String currency;

		/**
		 * The payment request total
		 */
		private BigDecimal value;

		/**
		 * The amount detail
		 */
		private AmountDetail detail;

		/**
		 * Sets the currency
		 *
		 * @param currency The currency to set
		 * @return This builder
		 * @see Amount#currency
		 */
		public Builder withCurrency(final String currency) {

			this.currency = currency;
			return this;
		}

		/**
		 * Sets the amounts total
		 *
		 * @param value The total to set
		 * @return This builder
		 * @see Amount#total
		 */
		public Builder withTotalValue(final BigDecimal value) {

			this.value = value;
			return this;
		}

		/**
		 * Sets the amount detail information
		 *
		 * @param detail The amount detail to set
		 * @return This builder
		 * @see Amount#detail
		 */
		public Builder withDetail(final AmountDetail detail) {

			this.detail = detail;
			return this;
		}

		/**
		 * Creates and returns a new {@linkplain Amount} instance configured with the given calues
		 *
		 * @return A new {@linkplain Amount} instance
		 */
		public Amount build() {

			return new Amount(this);
		}

	}

}
