package pagos.payu.core.model.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import pagos.payu.core.validation.ValidDateFormat;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Builder
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PaymentCard {

	public static final String DEFAULT_PAYMENT_CARD_TYPE = "CREDIT";

	/**
	 * Type ofType the card
	 */
	@Pattern(regexp = "CREDIT|DEBIT|PREPAID")
	@NotNull
	private String type;

	/**
	 * Card holder's full name: The name of the customer to whom a payment card is issued to or any individual
	 * authorized to use the payment card.
	 */
	@NotBlank
	@Length(min = 1, max = 255)
	@JsonProperty("name")
	private String cardHolderName;

	/**
	 * Unique payment card number (typically for credit or debit cards) that identifies the issuer and the particular
	 * cardholder account.
	 */
	@NotBlank
	@Length(min = 13, max = 20)
	@Pattern(regexp = "[0-9]{13,20}")
	private String number;

	/**
	 * Expiration month in two digits format (1= January, 2=February, ..., 12=December)
	 */
	//@NotNull
	//@Range(min = 1, max = 12)
	//private Integer expMonth;

	/**
	 * Expiration year in two digits format (Starting with 00 = 2000, ends with 99 = 2099)
	 */
	//@NotNull
	//@Range(min = 0, max = 99)
	//private Integer expYear;
	@ValidDateFormat
	@NotBlank
	private String expirationDate;

	/**
	 * Also known as Card Validation Code or Value, or Card Security Code.Refers to either: (1) magnetic-stripe data, or
	 * (2) printed security features.
	 * <p>
	 * Data element on a card's magnetic stripe that uses secure cryptographic processes to protect data integrity on
	 * the stripe, and reveals any alteration or counterfeiting. Referred to as CAV, CVC, CVV, or CSC depending on
	 * payment card brand.
	 * <p>
	 * For Discover, JCB, MasterCard, and Visa payment cards, the second type of card verification value or code is the
	 * rightmost three-digit value printed in the signature panel area on the back of the card. For American Express
	 * payment cards, the code is a four-digit unembossed number printed above the PAN on the face of the payment cards.
	 * The code is uniquely associated with each individual piece of plastic and ties the PAN to the plastic.
	 */
	@Length(min = 3, max = 4)
	private String securityCode;

	/**
	 * Credit card token (Used to mask the credit card information when it is already securely stored at PayU systems.
	 */
	@Length(min = 36, max = 36)
	private String token;

	/**
	 * The financial institution that issues cards to consumers on behalf of the card networks (Visa, MasterCard, etc).
	 * The issuing bank is also known as the credit or debit card company.
	 */
	@Length(max = 255)
	private String issuerBank;

	/**
	 * The ISO 3166 (Alpha-2) code of the expedition country.
	 */
	@Length(min = 2, max = 2)
	private String country;

	/**
	 * Flag that indicates if the request must be processed without security code verification. This is useful for
	 * cases like recurring payments, where only the number and expiration date is stored.
	 */
	private boolean processWithoutCvv2;

	/**
	 * Private constructor, required by Jackson
	 */
	private PaymentCard() {

		super();
	}

}
