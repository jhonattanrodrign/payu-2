package pagos.payu.core.model.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

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
	@NotNull
	@Range(min = 1, max = 12)
	private Integer expMonth;

	/**
	 * Expiration year in two digits format (Starting with 00 = 2000, ends with 99 = 2099)
	 */
	@NotNull
	@Range(min = 0, max = 99)
	private Integer expYear;

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

	/**
	 * Credit card constructor from builder
	 *
	 * @param builder PaymentCard builder
	 */
	public PaymentCard(final Builder builder) {

		this.type = builder.type;
		this.cardHolderName = builder.cardHolderName;
		this.number = builder.number;
		this.expMonth = builder.expMonth;
		this.expYear = builder.expYear;
		this.securityCode = builder.securityCode;
		this.token = builder.token;
		this.issuerBank = builder.issuerBank;
		this.country = builder.country;
		this.processWithoutCvv2 = builder.processWithoutCvv2;

	}

	/**
	 * Returns the payment card type
	 *
	 * @return The payment card type
	 */
	public String getType() {

		return type;
	}

	/**
	 * Returns the cardHolderName
	 *
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {

		return cardHolderName;
	}

	/**
	 * Returns the number
	 *
	 * @return the number
	 */
	public String getNumber() {

		return number;
	}

	/**
	 * Returns the expMonth
	 *
	 * @return the expMonth
	 */
	public Integer getExpMonth() {

		return expMonth;
	}

	/**
	 * Returns the expiration year
	 *
	 * @return The expiration year
	 */
	public Integer getExpYear() {

		return expYear;
	}

	/**
	 * Returns the security code
	 *
	 * @return The credit card security code
	 */
	public String getSecurityCode() {

		return securityCode;
	}

	/**
	 * Return the credit card token
	 *
	 * @return The credit card token
	 */
	public String getToken() {

		return token;
	}

	/**
	 * Return the name of the issuer bank
	 *
	 * @return The name of the issuer bank
	 */
	public String getIssuerBank() {

		return issuerBank;
	}

	/**
	 * Returns the country of expedition
	 *
	 * @return The country of expedition
	 */
	public String getCountry() {

		return country;
	}

	/**
	 * Returns the value of the flag that indicates if the card must be processed without security code validation
	 *
	 * @return The value of the flag that indicates if the card must be processed without security code validation
	 */
	public boolean isProcessWithoutCvv2() {

		return processWithoutCvv2;
	}

	/**
	 * reates a new {@linkplain Builder Payment Card Builder} and sets its type
	 *
	 * @param type The payment card type to set
	 * @return A new {@linkplain Builder Payment Card Builder} instance
	 */
	public static Builder ofType(final String type) {

		return new Builder().ofType(type);
	}

	/**
	 * Creates a new {@linkplain Builder Payment Card Builder} and sets its number
	 *
	 * @param number The payment card number to set
	 * @return A new {@linkplain Builder Payment Card Builder} instance
	 */
	public static Builder withNumber(final String number) {

		return new Builder().withNumber(number);
	}

	/**
	 * Creates a new {@linkplain Builder Credit Card Builder} and sets its token
	 *
	 * @param token The credit card token
	 * @return A new {@linkplain Builder Credit Card Builder} instance
	 */
	public static Builder ofToken(final String token) {

		return new Builder().withToken(token);
	}

	/**
	 * Builder class for {@linkplain PaymentCard} entity
	 *
	 * @author Manuel E. Vieda (manuel.vieda@payulatam.com)
	 * @date 04/08/2016
	 */
	public static class Builder {

		/**
		 * Type ofType the card
		 */
		private String type;

		/**
		 * Payment Card cardholder name
		 */
		private String cardHolderName;

		/**
		 * Payment card number
		 */
		private String number;

		/**
		 * Payment card expiration month
		 */
		private Integer expMonth;

		/**
		 * Payment card expiration year
		 */
		private Integer expYear;

		/**
		 * Payment card security code
		 */
		private String securityCode;

		/**
		 * Payment card token
		 */
		private String token;

		/**
		 * The financial institution that issues cards to consumers on behalf of the card networks (Visa, MasterCard,
		 * etc).
		 * The issuing bank is also known as the credit or debit card company.
		 */
		private String issuerBank;

		/**
		 * The ISO 3166 (Alpha-2) code of the expedition country.
		 */
		private String country;

		/**
		 * Flag that indicates if the request must be processed without security code verification. This is useful for
		 * cases like recurring payments, where only the number and expiration date is stored.
		 */
		private boolean processWithoutCvv2;

		/**
		 * Sets the type ofType the card
		 *
		 * @param type The type to set
		 * @return This builder
		 * @see PaymentCard#type
		 */
		public Builder ofType(final String type) {

			this.type = type;
			return this;
		}

		/**
		 * Sets the cardholder name
		 *
		 * @param cardHolderName The cardholder name to set
		 * @return This builder
		 * @see PaymentCard#cardHolderName
		 */
		public Builder withCardholderName(final String cardHolderName) {

			this.cardHolderName = cardHolderName;
			return this;
		}

		/**
		 * Sets the Payment card number
		 *
		 * @param number The Payment card number to set
		 * @return This builder
		 * @see PaymentCard#number
		 */
		public Builder withNumber(final String number) {

			this.number = number;
			return this;
		}

		/**
		 * Sets the expiration date
		 *
		 * @param expMonth The expiration month
		 * @param expYear The expiration year
		 * @return This builder
		 * @see PaymentCard#expMonth
		 * @see PaymentCard#expYear
		 */
		public Builder withExpirationDate(final Integer expMonth, final Integer expYear) {

			this.expMonth = expMonth;
			this.expYear = expYear;
			return this;
		}

		/**
		 * Sets the payment card security code
		 *
		 * @param securityCode The security code to set
		 * @return This builder
		 * @see PaymentCard#securityCode
		 */
		public Builder withSecurityCode(final String securityCode) {

			this.securityCode = securityCode;
			return this;
		}

		/**
		 * Sets the payment card token
		 *
		 * @param token The token to set
		 * @return This builder
		 * @see PaymentCard#token
		 */
		public Builder withToken(final String token) {

			this.token = token;
			return this;
		}

		/**
		 * Sets the issuer bank
		 *
		 * @param issuerBank The issuer bank
		 * @return This builder
		 * @see PaymentCard#issuerBank
		 */
		public Builder withIssuerBank(final String issuerBank) {

			this.issuerBank = issuerBank;
			return this;
		}

		/**
		 * Sets the country of expedition
		 *
		 * @param country The country of expedition to set
		 * @return This builder
		 * @see PaymentCard#country
		 */
		public Builder withCountry(final String country) {

			this.country = country;
			return this;
		}

		/**
		 * Set the flag to process the card without security code validation
		 *
		 * @return This builder
		 * @see PaymentCard#processWithoutCvv2
		 */
		public Builder processWithoutCvv2() {

			this.processWithoutCvv2 = true;
			return this;
		}

		/**
		 * Creates and returns a new {@linkplain PaymentCard} with the configured values
		 *
		 * @return A new configured {@linkplain PaymentCard}
		 */
		public PaymentCard build() {

			if (type == null) {
				type = DEFAULT_PAYMENT_CARD_TYPE;
			}
			return new PaymentCard(this);
		}

	}
}
