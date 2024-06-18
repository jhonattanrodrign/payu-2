package pagos.payu.application.caseuses.impl;

import java.math.BigDecimal;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import pagos.payu.core.model.common.TransactionWorkflowStatus;
import pagos.payu.infrastructure.dto.antifraud.AntifraudRequest;
import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.infrastructure.dto.capture.CaptureResponse;
import pagos.payu.core.model.common.AntiFraudPreValidationResponse;
import pagos.payu.core.model.common.AntiFraudValidationState;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.application.caseuses.UseCaseCapture;

@Slf4j
public class UseCaseCaptureImpl implements UseCaseCapture {

	@Override
	public TransactionResponse processRequest(final Transaction captureTransaction, final CaptureRequest captureRequest) {

		if(null == captureTransaction.getFraudStatus()) {
			AntiFraudPreValidationResponse antifraudResponse = createAntifraudRequest(captureTransaction);
			captureTransaction.setFraudStatus(antifraudResponse.getDecision());
		}
		return sendAuthorizationRequestAcquirerNetwork(captureRequest, captureTransaction);
	}


	private TransactionResponse sendAuthorizationRequestAcquirerNetwork(final CaptureRequest captureRequest, Transaction captureTransaction) {

		CaptureResponse captureResponse = sendAuthorizationRequestToAcquirerNetwork(captureRequest);
		captureResponse.setTransactionId(captureTransaction.getTransactionId().toString());
		captureResponse.setOrderId(captureTransaction.getOrderId());
		return evaluateAuthorizationResponse(captureResponse, captureRequest);
	}

	private TransactionResponse evaluateAuthorizationResponse(final CaptureResponse captureResponse, CaptureRequest captureRequest) {
		if(captureResponse != null && "0".equals(captureResponse.getTraceabilityCode())
				&& "APPROVED".equals(captureResponse.getAuthorizationCode())){
			return TransactionResponse.builder()
									  .authorizationCode("0")
									  .orderId(captureResponse.getOrderId())
									  .paymentNetworkResponseCode("APPROVED")
									  .responseCode("APROBADA")
									  .state(TransactionWorkflowStatus.CAPTURED.name())
									  .transactionId(captureResponse.getTransactionId())
									  .paymentNetworkResponseErrorMessage(null)
									  .traceabilityCode("1")
									  .operationDate(new Date())
									  .pendingReason(null)
									  .travelAgencyAuthorizationCode("1")
									  .build();
		}else{
			return TransactionResponse.builder()
									  .authorizationCode("1")
									  .orderId(captureResponse.getOrderId())
									  .paymentNetworkResponseCode("REJECTED")
									  .responseCode("Rechazado")
									  .state(TransactionWorkflowStatus.DECLINED.name())
									  .transactionId(captureResponse.getTransactionId())
									  .paymentNetworkResponseErrorMessage("Balance in 0")
									  .traceabilityCode("2")
									  .operationDate(new Date())
									  .pendingReason(null)
									  .travelAgencyAuthorizationCode("2")
									  .build();
		}
	}

	private CaptureResponse sendAuthorizationRequestToAcquirerNetwork(final CaptureRequest captureRequest) {

		return CaptureResponse.builder()
				.authorizationCode("APPROVED")
				.traceabilityCode("0")
				.build();
	}

	private AntiFraudPreValidationResponse createAntifraudRequest(final Transaction transaction) {

		AntifraudRequest request= new AntifraudRequest();
		request.setAccountId(1);
		request.setValue(BigDecimal.ONE);
		request.setMerchantId(transaction.getMerchantId());
		request.setTransactionId(transaction.getTransactionId());
		request.setOrderId(transaction.getOrderId());

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
