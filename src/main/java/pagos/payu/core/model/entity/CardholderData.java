package pagos.payu.core.model.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class CardholderData {

	public static final int ENUM_LENGTH_64 = 64;

	/**
	 * The class UID version.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Issuer identification number (IIN) corresponds to the first six digits of a card number
	 */
	private static final int IIN_LENGTH = 6;

	/**
	 * The name of the customer to whom a payment card is issued to or any individual authorized to
	 * use the payment card.
	 */
	private String cardholderName;

	/**
	 * Acronym for “primary account number” and also referred to as “account number.” Unique payment
	 * card number (typically for credit or debit cards) that identifies the issuer and the
	 * particular cardholder account.
	 */
	private String pan;

	/**
	 * Secure representation of the PAN through the concealment of some characters. This number must
	 * be used for display or print purposes, when there is no business requirement to view the
	 * entire PAN.
	 */
	private String maskedPan;

	/**
	 * The expiration date of the payment card
	 */
	private String expirationDate;

	public String verificationCode;

	/**
	 * A security token that resembles the original sensitive data using tokenization through the
	 * StrongKey Lite Encryption System (SKLES) appliance.
	 */
	public String token;

	public String verificationCodeToken;


	public static long getSerialVersionUID() {

		return serialVersionUID;
	}

	@Transient
	public static int getIinLength() {

		return IIN_LENGTH;
	}

	@NotNull
	@Length(min = 1, max = 255)
	public String getCardholderName() {

		return cardholderName;
	}

	public void setCardholderName(final String cardholderName) {

		this.cardholderName = cardholderName;
	}

	@Length(min = 13, max = 20)
	@Transient
	public String getPan() {

		return pan;
	}

	public void setPan(final String pan) {

		this.pan = pan;
	}

	@NotNull
	@Length(min = 13, max = 20)
	public String getMaskedPan() {

		return maskedPan;
	}

	public void setMaskedPan(final String maskedPan) {

		this.maskedPan = maskedPan;
	}

	@Transient
	@Length(min = 7, max = 7)
	@Pattern(regexp = "\\d{4}/\\d{2}")
	public String getExpirationDate() {

		return expirationDate;
	}

	public void setExpirationDate(final String expirationDate) {

		this.expirationDate = expirationDate;
	}

	public String getVerificationCode() {

		return verificationCode;
	}

	public void setVerificationCode(final String verificationCode) {

		this.verificationCode = verificationCode;
	}

	@Length(max = ENUM_LENGTH_64)
	public String getToken() {

		return token;
	}

	public void setToken(final String token) {

		this.token = token;
	}

	public String getVerificationCodeToken() {

		return verificationCodeToken;
	}

	public void setVerificationCodeToken(final String verificationCodeToken) {

		this.verificationCodeToken = verificationCodeToken;
	}


}
