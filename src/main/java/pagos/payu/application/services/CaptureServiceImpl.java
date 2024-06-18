package pagos.payu.application.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pagos.payu.core.model.common.TransactionWorkflowStatus;
import pagos.payu.core.services.CaptureService;
import pagos.payu.infrastructure.adapters.AcquirerNetworkService;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.infrastructure.dto.authorization.AuthorizationResponse;
import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.core.model.common.TransactionType;
import pagos.payu.application.caseuses.UseCaseCapture;
import pagos.payu.infrastructure.dto.capture.CaptureResponse;
import pagos.payu.infrastructure.repository.TransactionRepository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CaptureServiceImpl implements CaptureService {

	private final UseCaseCapture useCaseCapture;

	private final TransactionRepository transactionRepository;

	private AcquirerNetworkService acquirerNetworkService;

	@Override
	public TransactionResponse processRequest(final Integer orderId, final String authorizationId,final CaptureRequest captureRequest) throws Exception {

		Optional<Transaction> authorization = getAuthorization(authorizationId);

		if(!authorization.isPresent() || authorization.isEmpty()){
			throw new Exception("La authorization previa no existe"+orderId);
		}
		Transaction captureTransaction = convertTransaction(captureRequest,authorization.get());

		TransactionResponse response = useCaseCapture.processRequest(captureTransaction, captureRequest);

		saveCapture(captureTransaction, response);

		return response;
	}

	private Transaction convertTransaction(final CaptureRequest captureRequest, final Transaction authorization) {

		Transaction capture = new Transaction();
		capture.setTotal(authorization.getTotal());
		capture.setOrderId(authorization.getOrderId());
		capture.setCurrency(authorization.getCurrency());
		capture.setFraudStatus(authorization.getFraudStatus());
		capture.setStatus(authorization.getStatus());
		capture.setParentTransactionId(authorization.getTransactionId());
		capture.setExpirationDate(authorization.getExpirationDate());
		capture.setPaymentMethod(authorization.getPaymentMethod());
		capture.setCreationDate(authorization.getCreationDate());
		capture.setPaymentMethodMainName(authorization.getPaymentMethodMainName());
		capture.setLastUpdatedDate(authorization.getLastUpdatedDate());
		capture.setIsPaymentAttempt(authorization.getIsPaymentAttempt());
		capture.setMerchantId(authorization.getMerchantId());
		// todo set creditcardID -> capture.set(authorization.getIsPaymentAttempt());
		capture.setOrderId(captureRequest.getOrderId());
		capture.setType(TransactionType.CAPTURE);
		capture.setTransactionId(UUID.randomUUID());
		return capture;
	}

	private Optional<Transaction> getAuthorization( final String authorizationId) {

		return transactionRepository.findById(UUID.fromString(authorizationId));
	}

	private Transaction saveCapture(Transaction captureTransaction, TransactionResponse response) {

		captureTransaction.setStatus(TransactionWorkflowStatus.valueOf(response.getState()));
		captureTransaction.setResponseCode(response.getResponseCode());
		captureTransaction.setPaymentNetworkResponseCode(response.getPaymentNetworkResponseCode());
		captureTransaction.setTraceabilityCode(response.getTraceabilityCode());
		return transactionRepository.save(captureTransaction);
	}

	public TransactionResponse sendCaptureRequestAcquirerNetwork(final CaptureRequest captureRequest) {
		// Enviar solicitud de autorización a la red del adquirente
		CaptureResponse captureResponse = acquirerNetworkService.sendCaptureRequestToAcquirerNetwork(captureRequest);
		// Evaluar la respuesta de autorización
		return evaluateCaptureResponse(captureResponse);
	}

	private TransactionResponse evaluateCaptureResponse(CaptureResponse authorizationResponse) {
		TransactionResponse transactionResponse = new TransactionResponse();
		// Asumimos que `TransactionResponse` tiene un método para establecer el estado y detalles
		//transactionResponse.setStatus(authorizationResponse.getStatus());
		//transactionResponse.setDetails(authorizationResponse.getDetails());
		return transactionResponse;
	}
}
