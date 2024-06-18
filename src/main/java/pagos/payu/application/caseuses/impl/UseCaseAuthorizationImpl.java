package pagos.payu.application.caseuses.impl;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;
import pagos.payu.application.caseuses.UseCaseAuthorization;
import pagos.payu.core.model.common.TransactionWorkflowStatus;
import pagos.payu.core.provider.CreditCardProvider;
import pagos.payu.core.provider.OrderProvider;
import pagos.payu.core.provider.TransactionProvider;
import pagos.payu.infrastructure.dto.antifraud.AntifraudRequest;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.infrastructure.dto.authorization.AuthorizationResponse;
import pagos.payu.core.model.common.AntiFraudPreValidationResponse;
import pagos.payu.core.model.common.AntiFraudValidationState;
import pagos.payu.core.model.entity.Order;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.core.model.common.TransactionResponse;

@Slf4j
public class UseCaseAuthorizationImpl implements UseCaseAuthorization {

	private final TransactionProvider transactionProvider;

	private final OrderProvider orderProvider;

	private final CreditCardProvider creditCardProvider;

	public UseCaseAuthorizationImpl(TransactionProvider transactionProvider, OrderProvider orderProvider, CreditCardProvider creditCardProvider) {
		this.transactionProvider = transactionProvider;
		this.orderProvider = orderProvider;
		this.creditCardProvider = creditCardProvider;
	}

	@Override
	public Transaction buildTransactionByAuthorizationRequest(final Order order, final Transaction transaction, final AuthorizationRequest authorizationRequest, AntiFraudPreValidationResponse fraudResponse) {

		Transaction transactionPersisted;
		//todo CREATE CREDIT CARD with repository

		//AntiFraudPreValidationResponse response = createAntifraudRequest(order,  transaction);
		if (AntiFraudValidationState.DECLINED.equals(fraudResponse.getAntifraudValidationState()) && "DECLINED".equals(fraudResponse.getDecision())){
			transaction.setStatus(TransactionWorkflowStatus.DECLINED);
			transaction.setFraudStatus(fraudResponse.getDecision());
		}else{
			transaction.setFraudStatus(fraudResponse.getDecision());
		}

		transactionPersisted = transactionProvider.saveTransaction(transaction);
		orderProvider.saveOrder(order);

		return Objects.nonNull(transactionPersisted)?transactionPersisted:transaction;
	}

	@Override
	public TransactionResponse processAuthorizationRequest(Order order, Transaction transaction, AuthorizationRequest authorizationRequest) throws ExecutionException {

		return sendAuthorizationRequestAcquirerNetwork(authorizationRequest, transaction);
	}

	private TransactionResponse sendAuthorizationRequestAcquirerNetwork(final AuthorizationRequest authorizationRequest, Transaction transaction) {

		TransactionResponse response = new TransactionResponse();
		if(AntiFraudValidationState.APPROVED.toString().equals(transaction.getFraudStatus())) {
			AuthorizationResponse authorizationResponse = sendAuthorizationRequestToAcquirerNetwork(authorizationRequest, transaction);
			response = evaluateAuthorizationResponse(authorizationResponse);
			response.setTransactionId(transaction.getTransactionId().toString());
			response.setOrderId(transaction.getOrderId());
			return response;
		}
		response.setOrderId(transaction.getOrderId());
		response.setTransactionId(transaction.getTransactionId().toString());
		response.setResponseCode("DECLINED BY FRAUD");
		return response;
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

	private AuthorizationResponse sendAuthorizationRequestToAcquirerNetwork(final AuthorizationRequest authorizationRequest, Transaction transaction) {

		return AuthorizationResponse.builder()
				.ordenId(transaction.getOrderId().toString())
				.transationId(transaction.getTransactionId().toString())
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
				.processingTime("0000012")
				.providerAntiFraud("FEEDZAI")
				.build();

	}

}
