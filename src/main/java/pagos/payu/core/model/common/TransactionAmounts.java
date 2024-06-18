package pagos.payu.core.model.common;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Table(name = "transaccion_montos")
public class TransactionAmounts {

	/**
	 * Length for UUIDs
	 */
	public static final int UUID_LENGTH = 36;

	/**
	 * Minimum decimal value for positive decimal values only
	 */
	public static final String DECIMAL_VALUE_MIN = "0.00";

	/**
	 * Maximum decimal value for decimal values
	 */
	public static final String DECIMAL_VALUE_MAX = "999999999999.99";

	private BigDecimal totalAmount;

	private String currency;

	private BigDecimal tax;

	private BigDecimal taxBase;

	private String id;

	private Integer orderId;
	private BigDecimal administrativeFee;
	private BigDecimal administrativeFeeTax;


	@DecimalMin(DECIMAL_VALUE_MIN)
	@DecimalMax(DECIMAL_VALUE_MAX)
	public BigDecimal getAdministrativeFee() {

		return administrativeFee;
	}

	public void setAdministrativeFee(final BigDecimal administrativeFee) {

		this.administrativeFee = administrativeFee;
	}

	@DecimalMin(DECIMAL_VALUE_MIN)
	@DecimalMax(DECIMAL_VALUE_MAX)
	public BigDecimal getAdministrativeFeeTax() {

		return administrativeFeeTax;
	}

	public void setAdministrativeFeeTax(final BigDecimal administrativeFeeTax) {

		this.administrativeFeeTax = administrativeFeeTax;
	}

	@NotNull
	@DecimalMin(DECIMAL_VALUE_MIN)
	@DecimalMax(DECIMAL_VALUE_MAX)
	public String getValue() {

		return value;
	}

	public void setValue(final String value) {

		this.value = value;
	}

	/**
	 * The payment value
	 */
	private String value;

	@Id
	@Column(name = "transaccion_id",
			unique = true,
			length = UUID_LENGTH,
			nullable = false,
			updatable = false)
	@Size(min = UUID_LENGTH, max = UUID_LENGTH)
	public String getId() {

		return id;
	}

	public void setId(final String id) {

		this.id = id;
	}

	@Column(name = "orden_id", nullable = false, updatable = false)
	@NotNull
	public Integer getOrderId() {

		return orderId;
	}

	public void setOrderId(final Integer orderId) {

		this.orderId = orderId;
	}

	@Column(name = "pm_payer_total_amount", scale = 19, precision = 2)
	@DecimalMin(DECIMAL_VALUE_MIN)
	@DecimalMax(DECIMAL_VALUE_MAX)
	public BigDecimal getTotalAmount() {

		return totalAmount;
	}

	public void setTotalAmount(final BigDecimal totalAmount) {

		this.totalAmount = totalAmount;
	}

	@NotNull
	@Length(min = 3, max = 3)
	public String getCurrency() {

		return currency;
	}

	public void setCurrency(final String currency) {

		this.currency = currency;
	}

	@DecimalMin(DECIMAL_VALUE_MIN)
	@DecimalMax(DECIMAL_VALUE_MAX)
	public BigDecimal getTax() {

		return tax;
	}

	public void setTax(final BigDecimal tax) {

		this.tax = tax;
	}

	@DecimalMin(DECIMAL_VALUE_MIN)
	@DecimalMax(DECIMAL_VALUE_MAX)
	public BigDecimal getTaxBase() {

		return taxBase;
	}

	public void setTaxBase(final BigDecimal taxBase) {

		this.taxBase = taxBase;
	}


}
