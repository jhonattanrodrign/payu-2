package pagos.payu.core.model.common;

public enum AntiFraudValidationState {

	/**
	 * The transaction was evaluated and successfully verified by the antifraud.
	 */
	VERIFIED,

	/**
	 * The transaction was evaluated and rejected by the antifraud.
	 */
	REJECTED,

	/**
	 * The transaction was not evaluated verified nor rejected) by the antifraud.
	 */
	SKIPPED,

	/**
	 * The transaction was still being evaluated by the antifraud system. The adapter response code is pending. This
	 * means that additional validation tasks are required and cannot confirm the final state until those tasks are
	 * completed.
	 */
	PENDING,

	/**
	 * The credit card is international and the merchant is not allowed to process international cards.
	 */
	INVALID_INTERNATIONAL_CARD
}
