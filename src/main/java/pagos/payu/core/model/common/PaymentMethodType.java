package pagos.payu.core.model.common;

public enum PaymentMethodType {

	/**
	 * Payment with credit card
	 */
	CREDIT_CARD;

	/**
	 * Flag to indicates if the payment method can be validated against an AntiFraud system.
	 */
	private final boolean antifraudValidationAvailable;

	/**
	 * Payment method type constructor. By default set to {@code true} the anti-fraud validation.
	 */
	PaymentMethodType() {

		antifraudValidationAvailable = true;
	}

	/**
	 * Payment method type constructor
	 *
	 * @param antifraudValidationAvailable Flag to indicates if the payment method can be validated
	 * against an AntiFraud system.
	 */
	PaymentMethodType(final boolean antifraudValidationAvailable) {

		this.antifraudValidationAvailable = antifraudValidationAvailable;
	}

	/**
	 * Returns the antifraudValidation
	 *
	 * @return the antifraudValidation
	 */
	public boolean isAntifraudValidation() {

		return antifraudValidationAvailable;
	}
}
