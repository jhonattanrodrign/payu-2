package pagos.payu.core.model.common;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractResponse {

	/**
	 * Identifier of the associated transaction.
	 */
	private String transactionId;

	/**
	 * The identifier of the order related to the transaction and its response
	 */
	private Integer orderId;

	/**
	 * The response code of the transaction. This response code is associated with the state of the
	 * transaction
	 */
	private String responseCode;

	/**
	 * The transaction state based on the response code
	 */
	private String state;

	/**
	 * The reason for Pending transactions
	 */
	private String pendingReason;

	/**
	 * The payment network response code
	 */
	private String paymentNetworkResponseCode;

	/**
	 * The error message related to the payment network response
	 */
	private String paymentNetworkResponseErrorMessage;


	/**
	 * Default constructor, required by Jackson
	 */
	protected AbstractResponse() {
		// Empty
	}

	/**
	 * Abstract response constructor from builder
	 *
	 * @param builder Abstract response builder
	 */
	protected AbstractResponse(final AbstractResponseBuilder builder) {

		this.transactionId = builder.transactionId;
		this.orderId = builder.orderId;
		this.responseCode = builder.responseCode;
		this.state = builder.state;
		this.pendingReason = builder.pendingReason;
		this.paymentNetworkResponseCode = builder.paymentNetworkResponseCode;
		this.paymentNetworkResponseErrorMessage = builder.paymentNetworkResponseErrorMessage;
	}



	/**
	 * Creates the Response from the Transaction Response entity
	 *
	 * @param transactionResponse The transaction response base entity
	 */

	public AbstractResponse(final TransactionResponse transactionResponse) {

		setTransactionId(transactionResponse.getTransactionId());
		setOrderId(transactionResponse.getOrderId());
		setState(transactionResponse.getState());
		setPendingReason(transactionResponse.getPendingReason());
		setResponseCode(transactionResponse.getResponseCode());
		setPaymentNetworkResponseCode(transactionResponse.getPaymentNetworkResponseCode());
		setPaymentNetworkResponseErrorMessage(transactionResponse.getPaymentNetworkResponseErrorMessage());
	}

	/**
	 * Builder class for {@linkplain AbstractResponse}
	 *
	 * @author Manuel E. Vieda (manuel.vieda@payulatam.com)
	 */
	public abstract static class AbstractResponseBuilder<T extends AbstractResponseBuilder> {

		/**
		 * Identifier of the associated transaction.
		 */
		private String transactionId;

		/**
		 * The identifier of the order related to the transaction and its response
		 */
		private Integer orderId;

		/**
		 * The response code of the transaction. This response code is associated with the state of the transaction
		 */
		private String responseCode;

		/**
		 * The transaction state based on the response code
		 */
		private String state;

		/**
		 * The reason for pending transactions
		 */
		private String pendingReason;

		/**
		 * The payment network response code
		 */
		private String paymentNetworkResponseCode;

		/**
		 * The error message related to the payment network response
		 */
		private String paymentNetworkResponseErrorMessage;

		/**
		 * Return the concrete builder instance
		 *
		 * @return The concrete builder instance
		 */
		public abstract T builderInstance();

		/**
		 * Sets the transaction identifier
		 *
		 * @param transactionId The transaction identifier
		 * @return The concrete builder implementation
		 * @see AbstractResponse#transactionId;
		 */
		public T withTransactionId(final String transactionId) {

			this.transactionId = transactionId;
			return builderInstance();
		}

		/**
		 * Sets the order identifier
		 *
		 * @param orderId The order identifier to set
		 * @return The concrete builder implementation
		 * @see AbstractResponse#orderId
		 */
		public T withOrderId(final Integer orderId) {

			this.orderId = orderId;
			return builderInstance();
		}

		/**
		 * Sets the response code
		 *
		 * @param responseCode The response code to set
		 * @return The concrete builder implementation
		 * @see AbstractResponse#responseCode
		 */
		public T wihtResponsecode(final String responseCode) {

			this.responseCode = responseCode;
			return builderInstance();
		}

		/**
		 * Sets the state
		 *
		 * @param state The state to set
		 * @return The concrete builder implementation
		 * @see AbstractResponse#state
		 */
		public T withState(final String state) {

			this.state = state;
			return builderInstance();
		}

		/**
		 * Sets the pending reason
		 *
		 * @param pendingReason The pending reason to set
		 * @return The concrete builder implementation
		 * @see AbstractResponse#pendingReason
		 */
		public T withPendingReason(final String pendingReason) {

			this.pendingReason = pendingReason;
			return builderInstance();
		}

		/**
		 * Sets tje payment network response code
		 *
		 * @param paymentNetworkResponseCode The payment network response code to set
		 * @return The concrete builder implementation
		 * @see AbstractResponse#paymentNetworkResponseCode
		 */
		public T withPaymentNetworkResponseCode(final String paymentNetworkResponseCode) {

			this.paymentNetworkResponseCode = paymentNetworkResponseCode;
			return builderInstance();
		}

		/**
		 * Sets the payment network response error message
		 *
		 * @param paymentNetworkResponseErrorMessage The payment network response error message to set
		 * @return The concrete builder implementation
		 * @see AbstractResponse#paymentNetworkResponseErrorMessage
		 */
		public T wihtPaymentNetworkResponseErrorMessage(final String paymentNetworkResponseErrorMessage) {

			this.paymentNetworkResponseErrorMessage = paymentNetworkResponseErrorMessage;
			return builderInstance();
		}

		/**
		 * Add the given extra parameters
		 *
		 * @param extraParameters The extra parameters to add
		 * @return The concrete builder implementation
		 */
		/*
		public T addExtraParameters(final Map<String, String> extraParameters) {

			if (extraParameters != null) {
				createExtraParameters();
				this.extraParameters.putAll(extraParameters);
			}
			return builderInstance();
		}


		 */
		/**
		 * Add the given key-value pair to the extra parameters
		 *
		 * @param key The key of the extra parameter
		 * @param value The value of the extra parameter
		 * @return The concrete builder implementation
		 */
		/*
		public T addExtraParameter(final String key, final String value) {

			if (StringUtils.isNotBlank(key) && value != null) {
				createExtraParameters();
				this.extraParameters.put(key, value);
			}
			return builderInstance();
		}

		 */

		/**
		 * Creates the extra parameters map
		 */
		/*
		private void createExtraParameters() {

			if (extraParameters == null) {
				extraParameters = new HashMap<>();
			}
		}

		 */

	}

}
