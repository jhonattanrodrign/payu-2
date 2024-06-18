package pagos.payu.infrastructure.controller;

import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import pagos.payu.core.impl.UseCaseAuthorizationImpl;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.infrastructure.dto.authorization.AuthorizationResponse;
import pagos.payu.core.model.common.Order;
import pagos.payu.core.model.common.Transaction;
import pagos.payu.core.model.common.TransactionResponse;

@RestController
@RequestMapping(path = "/payment",
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class AuthorizationController {

	/**
	 * Default class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);

	private final UseCaseAuthorizationImpl useCaseAuthorization;


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AuthorizationResponse> doAuthorization(@Valid @RequestBody final AuthorizationRequest authorizationRequest) {

		Order order = useCaseAuthorization.toOrder(authorizationRequest);
		Transaction transaction = useCaseAuthorization.toTransaction(authorizationRequest);
		try{
			final TransactionResponse transactionResponse =
					useCaseAuthorization.processAuthorizationRequest(order, transaction, authorizationRequest);
			return new ResponseEntity<>(new AuthorizationResponse(transactionResponse), HttpStatus.OK);
		}catch (final RuntimeException e){

			LOGGER.error(
					"Error processing payment request. Error Message: [" + e.getMessage() +
							"]  Response: INTERNAL_SERVER_ERROR. AuthorizationRequest [" +
							getPaymentRequestDescription(authorizationRequest) + "]",
					e);

			return new ResponseEntity<>((AuthorizationResponse) null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String getPaymentRequestDescription(final AuthorizationRequest authorizationRequest) {

		if (authorizationRequest != null) {

			final StringBuilder description = new StringBuilder(150)
					.append("AccountId: [").append(authorizationRequest.getAccountId())
					.append("] MerchantId: [").append(authorizationRequest.getMerchantId())
					.append("] Reference: [").append(authorizationRequest.getReference())
					.append("] Capture: [").append(authorizationRequest.isCapture())
					.append("]");

			return description.toString();
		}

		return StringUtils.EMPTY;
	}

}
