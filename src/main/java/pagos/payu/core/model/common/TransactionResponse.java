package pagos.payu.core.model.common;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
@Data
@Builder
@AllArgsConstructor
public class TransactionResponse implements Serializable {

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
	 * Serial Version.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The authorization code
	 */
	private String authorizationCode;

	/**
	 * The traceability code related with the response
	 */
	private String traceabilityCode;

	/**
	 * The authorization code of the travel agency request
	 */
	private String travelAgencyAuthorizationCode;

	/**
	 * The operation date, this is the date the transaction response was created.
	 */
	private Date operationDate;

	public TransactionResponse() {

	}
}
