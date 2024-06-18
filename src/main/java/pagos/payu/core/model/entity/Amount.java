package pagos.payu.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import pagos.payu.core.model.common.Constants;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "amounts")
public class Amount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long amountId;

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
	@JsonProperty("value")
	private BigDecimal total;

	/**
	 * Amount detail
	 */
	@Valid
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "taxId")
	private List<Tax> tax;
}
