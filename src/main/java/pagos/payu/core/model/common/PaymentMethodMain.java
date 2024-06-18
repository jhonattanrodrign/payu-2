package pagos.payu.core.model.common;

public enum PaymentMethodMain {

	// Credit Card

	VISA(PaymentMethodType.CREDIT_CARD),
	AMEX(PaymentMethodType.CREDIT_CARD),
	DINERS(PaymentMethodType.CREDIT_CARD),
	MASTERCARD(PaymentMethodType.CREDIT_CARD),
	DISCOVER(PaymentMethodType.CREDIT_CARD),
	ELO(PaymentMethodType.CREDIT_CARD),
	SHOPPING(PaymentMethodType.CREDIT_CARD),
	NARANJA(PaymentMethodType.CREDIT_CARD),
	CABAL(PaymentMethodType.CREDIT_CARD),
	ARGENCARD(PaymentMethodType.CREDIT_CARD),
	PRESTO(PaymentMethodType.CREDIT_CARD),
	RIPLEY(PaymentMethodType.CREDIT_CARD),
	CENCOSUD(PaymentMethodType.CREDIT_CARD),
	TRANSBANK(PaymentMethodType.CREDIT_CARD);


	/**
	 * Payment method type
	 */
	private final PaymentMethodType type;

	/**
	 * Payment method main constructor
	 *
	 * @param type The payment method type
	 */
	PaymentMethodMain(final PaymentMethodType type) {

		this.type = type;
	}

	/**
	 * Returns the type
	 *
	 * @return the type
	 */
	public PaymentMethodType getType() {

		return type;
	}

}
