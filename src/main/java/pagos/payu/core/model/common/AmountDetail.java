package pagos.payu.core.model.common;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class AmountDetail {

	/**
	 * The tax value of the payment request. If the field is empty, the system assumes to be zero (All countries except
	 * for Colombia) or the 16% the total value (For Colombia)
	 */
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal tax;

	/**
	 * The assessed amount of the payment that is subject to taxation
	 */
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal taxBase;

	/**
	 * Non commissionable Additional Value
	 */
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal additionalValue;

	/**
	 * Value of the administrative fee.
	 */
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal administrativeFee;

	/**
	 * Tax value of the administrative fee
	 */
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal administrativeFeeTax;

	/**
	 * Base value of the administrative fee
	 */
	@DecimalMin(Constants.DECIMAL_VALUE_MIN)
	@DecimalMax(Constants.DECIMAL_VALUE_MAX)
	private BigDecimal administrativeFeeTaxBase;

	/**
	 * Private default constructor required by Jackson
	 */
	private AmountDetail() {

		super();
	}

	/**
	 * @param builder
	 */
	public AmountDetail(AmountDetail.Builder builder) {

		this.tax = builder.tax;
		this.taxBase = builder.taxBase;
		this.additionalValue = builder.additionalValue;
		this.administrativeFee = builder.administrativeFee;
		this.administrativeFeeTax = builder.administrativeFeeTax;
		this.administrativeFeeTaxBase = builder.administrativeFeeTaxBase;
	}

	/**
	 * Returns the tax
	 *
	 * @return the tax
	 */
	public BigDecimal getTax() {

		return tax;
	}

	/**
	 * Sets the tax
	 *
	 * @param tax the tax to set
	 */
	public void setTax(final BigDecimal tax) {

		this.tax = tax;
	}

	/**
	 * Returns the taxBase
	 *
	 * @return the taxBase
	 */
	public BigDecimal getTaxBase() {

		return taxBase;
	}

	/**
	 * Sets the taxBase
	 *
	 * @param taxBase the taxBase to set
	 */
	public void setTaxBase(final BigDecimal taxBase) {

		this.taxBase = taxBase;
	}

	/**
	 * Returns the additionalValue
	 *
	 * @return the additionalValue
	 */
	public BigDecimal getAdditionalValue() {

		return additionalValue;
	}

	/**
	 * Sets the additionalValue
	 *
	 * @param additionalValue the additionalValue to set
	 */
	public void setAdditionalValue(final BigDecimal additionalValue) {

		this.additionalValue = additionalValue;
	}

	/**
	 * Returns the administrativeFee
	 *
	 * @return the administrativeFee
	 */
	public BigDecimal getAdministrativeFee() {

		return administrativeFee;
	}

	/**
	 * Sets the administrativeFee
	 *
	 * @param administrativeFee the administrativeFee to set
	 */
	public void setAdministrativeFee(final BigDecimal administrativeFee) {

		this.administrativeFee = administrativeFee;
	}

	/**
	 * Returns the administrativeFeeTax
	 *
	 * @return the administrativeFeeTax
	 */
	public BigDecimal getAdministrativeFeeTax() {

		return administrativeFeeTax;
	}

	/**
	 * Sets the administrativeFeeTax
	 *
	 * @param administrativeFeeTax the administrativeFeeTax to set
	 */
	public void setAdministrativeFeeTax(final BigDecimal administrativeFeeTax) {

		this.administrativeFeeTax = administrativeFeeTax;
	}

	/**
	 * Returns the administrativeFeeTaxBase
	 *
	 * @return the administrativeFeeTaxBase
	 */
	public BigDecimal getAdministrativeFeeTaxBase() {

		return administrativeFeeTaxBase;
	}

	/**
	 * Sets the administrativeFeeTaxBase
	 *
	 * @param administrativeFeeTaxBase the administrativeFeeTaxBase to set
	 */
	public void setAdministrativeFeeTaxBase(final BigDecimal administrativeFeeTaxBase) {

		this.administrativeFeeTaxBase = administrativeFeeTaxBase;
	}

	/**
	 * @param tax
	 * @return
	 */
	public static Builder withTax(final BigDecimal tax) {

		return new Builder().withTax(tax);
	}

	/**
	 * @param administrativeFee
	 * @return
	 */
	public static Builder withAdministrativeFee(final BigDecimal administrativeFee) {

		return new Builder().withAdministrativeFee(administrativeFee);
	}

	/**
	 * Returns a string representation of the amounts detail, creating a "textually represents" of all the attributes.
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {

		return "AmountDetail{" +
				"tax=" + tax +
				", taxBase=" + taxBase +
				", additionalValue=" + additionalValue +
				", administrativeFee=" + administrativeFee +
				", administrativeFeeTax=" + administrativeFeeTax +
				", administrativeFeeTaxBase=" + administrativeFeeTaxBase +
				'}';
	}

	/**
	 * Builder class for {@linkplain Amount} entities
	 *
	 * @author Manuel E. Vieda (manuel.vieda@payulatam.com)
	 */
	public static class Builder {

		/**
		 * The tax value of the payment request. If the field is empty, the system assumes to be zero (All countries
		 * except for Colombia) or the 16% the total value (For Colombia)
		 */
		private BigDecimal tax;

		/**
		 * The assessed amount of the payment that is subject to taxation
		 */
		private BigDecimal taxBase;

		/**
		 * Non commissionable Additional Value
		 */
		private BigDecimal additionalValue;

		/**
		 * Value of the administrative fee.
		 */
		private BigDecimal administrativeFee;

		/**
		 * Tax value of the administrative fee
		 */
		private BigDecimal administrativeFeeTax;

		/**
		 * Base value of the administrative fee
		 */
		private BigDecimal administrativeFeeTaxBase;

		/**
		 * Sets the tax value
		 *
		 * @param tax The tax value to set
		 * @return This builder
		 * @see AmountDetail#tax
		 */
		public Builder withTax(final BigDecimal tax) {

			this.tax = tax;
			return this;
		}

		/**
		 * Sets the tax base value
		 *
		 * @param taxBase The tax base to set
		 * @return This builder
		 * @see AmountDetail#taxBase
		 */
		public Builder withTaxBase(final BigDecimal taxBase) {

			this.taxBase = taxBase;
			return this;
		}

		/**
		 * Sets an additional value
		 *
		 * @param additionalValue The additional value to set
		 * @return This builder
		 * @see AmountDetail#additionalValue
		 */
		public Builder withAdditionalValue(final BigDecimal additionalValue) {

			this.additionalValue = additionalValue;
			return this;
		}

		/**
		 * Sets the administrative fee value
		 *
		 * @param administrativeFee the administrative fee value to set
		 * @return This builder
		 * @see AmountDetail#administrativeFee
		 */
		public Builder withAdministrativeFee(final BigDecimal administrativeFee) {

			this.administrativeFee = administrativeFee;
			return this;
		}

		/**
		 * Sets the administrative fee tax value
		 *
		 * @param administrativeFeeTax The administrative fee tax value to set
		 * @return This builder
		 * @see AmountDetail#administrativeFee
		 */
		public Builder withAdministrativeFeeTax(final BigDecimal administrativeFeeTax) {

			this.administrativeFeeTax = administrativeFeeTax;
			return this;
		}

		/**
		 * Sets the administrative fee tax base value
		 *
		 * @param administrativeFeeTaxBase The administrative fee tax base value to set
		 * @return This builder
		 * @see AmountDetail#administrativeFeeTaxBase
		 */
		public Builder withAdministrativeFeeTaxBase(final BigDecimal administrativeFeeTaxBase) {

			this.administrativeFeeTaxBase = administrativeFeeTaxBase;
			return this;
		}

		/**
		 * Creates and returns a new {@linkplain Amount} instance configured with the given calues
		 *
		 * @return A new {@linkplain Amount} instance
		 */
		public AmountDetail build() {

			return new AmountDetail(this);
		}

	}

}
