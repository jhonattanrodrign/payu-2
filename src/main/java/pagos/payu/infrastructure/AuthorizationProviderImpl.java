package pagos.payu.infrastructure;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import pagos.payu.infrastructure.dto.antifraud.AntifraudRequest;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.infrastructure.dto.authorization.AuthorizationResponse;
import pagos.payu.core.model.common.AntiFraudPreValidationResponse;
import pagos.payu.core.model.common.AntiFraudValidationState;
import pagos.payu.core.model.common.Order;
import pagos.payu.core.model.common.Transaction;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.core.provider.AuthorizationProvider;

@Slf4j
public class AuthorizationProviderImpl implements AuthorizationProvider {

	@Override
	public TransactionResponse processAuthorizationRequest(final Order order, final Transaction transaction, final AuthorizationRequest authorizationRequest) {

		//todo create TRX with repository
		//todo create ORDER with repository
		//todo CREATE CREDIT CARD with repository

		AntiFraudPreValidationResponse response = createAntifraudRequest(order,  transaction);

		if(response != null){
			//todo update Transaction with response
			if(AntiFraudValidationState.VERIFIED.equals(response.getAntifraudValidationState())){
				return sendAuthorizationRequestAcquirerNetwork(authorizationRequest);
			}
		}
		return null;
	}

	private TransactionResponse sendAuthorizationRequestAcquirerNetwork(final AuthorizationRequest authorizationRequest) {

		AuthorizationResponse authorizationResponse = sendAuthorizationRequestToAcquirerNetwork(authorizationRequest);
		return evaluateAuthorizationResponse(authorizationResponse);


	}

	private TransactionResponse evaluateAuthorizationResponse(final AuthorizationResponse authorizationResponse) {
		if(authorizationResponse != null && "0".equals(authorizationResponse.getTraceabilityCode())
				&& "APPROVED".equals(authorizationResponse.getAuthorizationCode())){
			return TransactionResponse.builder()
					.authorizationCode("0")
					.orderId(authorizationResponse.getOrderId())
					.paymentNetworkResponseCode("APPROVED")
					.responseCode("APROBADA")
					.state("AUTHORIZED")
					.transactionId(authorizationResponse.getTransactionId())
					.paymentNetworkResponseErrorMessage(null)
					.traceabilityCode("1")
					.operationDate(new Date())
					.pendingReason(null)
					.travelAgencyAuthorizationCode("1")
					.build();
		}else{
			return TransactionResponse.builder()
					.authorizationCode("1")
					.orderId(authorizationResponse.getOrderId())
					.paymentNetworkResponseCode("REJECTED")
					.responseCode("Rechazado")
					.state("DECLINED")
					.transactionId(authorizationResponse.getTransactionId())
					.paymentNetworkResponseErrorMessage("Balance in 0")
					.traceabilityCode("2")
					.operationDate(new Date())
					.pendingReason(null)
					.travelAgencyAuthorizationCode("2")
					.build();
		}
	}

	private AuthorizationResponse sendAuthorizationRequestToAcquirerNetwork(final AuthorizationRequest authorizationRequest) {

		return AuthorizationResponse.builder()
				.authorizationCode("APPROVED")
				.traceabilityCode("0")
				.build();
	}

	private AntiFraudPreValidationResponse createAntifraudRequest(final Order order, final Transaction transaction) {

		AntifraudRequest request= new AntifraudRequest();
		request.setAccountId(order.getAccountId());
		request.setValue(order.getAmount());
		request.setMerchantId(order.getMerchantId());
		request.setTransactionId(transaction.getTransactionId());
		request.setOrderId(order.getOrderId());

		//todo send request to antifraud validator and separate it in this provider
		return antiFraudValidator(request);

	}

	private AntiFraudPreValidationResponse antiFraudValidator(final AntifraudRequest request) {

		return AntiFraudPreValidationResponse.builder()
				.antifraudValidationState(AntiFraudValidationState.VERIFIED)
				.codeResponse("0")
				.creditCardCountry("CO")
				.creditCardIssuerBank("BANCOLOMBIA")
				.decision("APPROVED")
				.errorCode(null)
				.errorMessage(null)
				.id(request.getTransactionId())
				.orderId(request.getOrderId())
				.neuralScore("0.1")
				.processingTime("0000012")
				.providerAntiFraud("FEEDZAI")
				.build();

	}

}
