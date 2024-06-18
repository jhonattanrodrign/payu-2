package pagos.payu.core.model.common;

public enum TransactionType {


	AUTHORIZATION(true),

	/**
	 * A Capture transaction is used to transfer previously authorized funds from the customer to
	 * the merchant after order fulfillment.
	 */
	CAPTURE(true),

	/**
	 * An authorization and capture process within a single call.
	 */
	AUTHORIZATION_AND_CAPTURE(true),

	/**
	 * A void transaction cancels the transfer of funds from the customer to the merchant (or PayU)
	 * and can be issued if the transaction is either Authorized or Submitted for Settlement.
	 * <p>
	 * When a transaction is voided, the original authorization should disappear from the customer’s
	 * statement within 24 to 48 hours.
	 */
	VOID,

	/**
	 * A refund transaction transfers funds from the merchant account to the customer’s account.
	 * Refunds are always associated with a transaction that has settled, meaning the funds have
	 * already transferred from the customer to the merchant (Or PayU).
	 * <p>
	 * A refunded transaction goes through the typical settlement process; as soon as the refund
	 * settles, the funds are sent back to the customer’s bank account.
	 */
	REFUND,

	/**
	 * A partial refund transaction is a regular refund transaction but only for a part of the total
	 * transaction value.
	 */
	PARTIAL_REFUND,

	/**
	 * A cancellation transaction is the abstraction for a refund or void, allowing the system the
	 * appropriate type depending on the country, time and the original transaction type.
	 */
	CANCELLATION,

	/**
	 * A chargeback transaction represents a request from a financial entity in behalf of a final
	 * customer claiming the refund of it funds to the bank account, line of credit, or credit card.
	 */
	CHARGEBACK;

	/**
	 * Determines if the transaction type is a payment attempt or not
	 */
	private final boolean isPaymentAttempt;

	/**
	 * Transaction type default constructor
	 */
	TransactionType() {

		isPaymentAttempt = false;
	}

	/**
	 * Transaction type constructor
	 *
	 * @param isPaymentAttempt Flag that Determines if the transaction type is a payment attempt
	 */
	TransactionType(final boolean isPaymentAttempt) {

		this.isPaymentAttempt = isPaymentAttempt;
	}

	/**
	 * Returns the isPaymentAttempt
	 *
	 * @return the isPaymentAttempt
	 */
	public boolean isPaymentAttempt() {

		return isPaymentAttempt;
	}

}
