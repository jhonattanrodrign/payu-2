package pagos.payu.infrastructure;

import java.math.BigDecimal;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import pagos.payu.infrastructure.dto.antifraud.AntifraudRequest;
import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.infrastructure.dto.capture.CaptureResponse;
import pagos.payu.core.model.common.AntiFraudPreValidationResponse;
import pagos.payu.core.model.common.AntiFraudValidationState;
import pagos.payu.core.model.common.Transaction;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.core.provider.CaptureProvider;

@Slf4j
public class CaptureProviderImpl implements CaptureProvider {

	@Override
	public TransactionResponse processRequest(final Transaction captureTransaction, final CaptureRequest captureRequest) {

		AntiFraudPreValidationResponse antifraudResponse= createAntifraudRequest(captureTransaction);
		if(antifraudResponse != null && AntiFraudValidationState.VERIFIED.equals(antifraudResponse.getAntifraudValidationState())){
			return sendAuthorizationRequestAcquirerNetwork(captureRequest);
		}
		return null;
	}


	private TransactionResponse sendAuthorizationRequestAcquirerNetwork(final CaptureRequest captureRequest) {

		CaptureResponse captureResponse = sendAuthorizationRequestToAcquirerNetwork(captureRequest);
		return evaluateAuthorizationResponse(captureResponse);


	}

	private TransactionResponse evaluateAuthorizationResponse(final CaptureResponse captureResponse) {
		if(captureResponse != null && "0".equals(captureResponse.getPaymentNetworkResponseCode())
				&& "APPROVED".equals(captureResponse.getResponseCode())){
			return TransactionResponse.builder()
									  .authorizationCode("0")
									  .orderId(captureResponse.getOrderId())
									  .paymentNetworkResponseCode("APPROVED")
									  .responseCode("APROBADA")
									  .state("AUTHORIZED")
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
									  .state("DECLINED")
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

		AntifraudRequest request2= AntifraudRequest.builder()
												  .accountId(1)
												  .merchantId(transaction.getMerchantId())
												  .transactionId(transaction.getTransactionId())
												  .value(BigDecimal.ONE)
												  .orderId(transaction.getOrderId()).build();

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
