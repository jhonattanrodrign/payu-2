package pagos.payu.core.model.common;

public enum TransactionWorkflowStatus {

	/**
	 * Status used to initialize the payment workflow.
	 */
	NEW,

	/**
	 * The transaction was approved by the anti fraud system (This status is only valid for
	 * authorization and capture transactions)
	 */
	VERIFIED,

	/**
	 * Status used to indicate an intermediate step before give an answer to client. In this state,
	 * the transaction is evaluated and decide if must be send to another payment network
	 * (Multi-acquirer) or marked as final.
	 */
	NETWORK_PROCESSED,

	/**
	 * The transaction have a final response that can be query by the merchant.
	 */
	PROCESSED,

	/**
	 * The transaction was query by the merchant and we assume that the transaction response was
	 * received.
	 */
	ACKNOWLEDGED
}
