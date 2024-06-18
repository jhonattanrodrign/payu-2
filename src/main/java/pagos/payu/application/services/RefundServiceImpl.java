package pagos.payu.application.services;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pagos.payu.application.caseuses.UseCaseRefund;
import pagos.payu.core.model.common.TransactionType;
import pagos.payu.core.model.common.TransactionWorkflowStatus;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.core.services.RefundService;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.infrastructure.dto.refund.RefundRequest;
import pagos.payu.infrastructure.repository.TransactionRepository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefundServiceImpl implements RefundService {

	private final UseCaseRefund useCaseRefund;

	private final TransactionRepository transactionRepository;

	@Override
	public TransactionResponse processRequest(final Integer orderId, final String captureId, final RefundRequest refundRequest) throws Exception {

		Optional<Transaction> capture = getCapture(orderId, captureId, refundRequest);

		if(!capture.isPresent() || !TransactionWorkflowStatus.CAPTURED.equals(capture.get().getStatus())){
			throw new Exception("The capture transaction don´t exists order Id");
		}

		Transaction refundTransaction = convertTransaction(refundRequest,capture.get());
		TransactionResponse response = useCaseRefund.processRequest(refundTransaction, refundRequest);
		saveCapture(refundTransaction,refundRequest, response);

		return response;
	}

	private Transaction convertTransaction(RefundRequest refundRequest, Transaction capture) {
		Transaction refund = new Transaction();
		refund.setTotal(capture.getTotal());
		refund.setOrderId(capture.getOrderId());
		refund.setStatus(capture.getStatus());
		refund.setParentTransactionId(capture.getTransactionId());
		refund.setCurrency(capture.getCurrency());
		refund.setFraudStatus(capture.getFraudStatus());
		refund.setCreationDate(capture.getCreationDate());
		refund.setPaymentMethodMainName(capture.getPaymentMethodMainName());
		refund.setExpirationDate(capture.getExpirationDate());
		refund.setPaymentMethod(capture.getPaymentMethod());
		refund.setLastUpdatedDate(capture.getLastUpdatedDate());
		refund.setIsPaymentAttempt(capture.getIsPaymentAttempt());
		refund.setMerchantId(capture.getMerchantId());
		// todo set creditcardID -> refund.set(capture.getIsPaymentAttempt());
		refund.setOrderId(refundRequest.getOrderId());
		refund.setType(TransactionType.REFUND);
		refund.setTransactionId(UUID.randomUUID());
		return refund;
	}

	private Optional<Transaction> getCapture(Integer orderId, String captureId, RefundRequest refundRequest) {
		return transactionRepository.findById(UUID.fromString(captureId));
	}

	private Transaction saveCapture(Transaction refundTransaction, RefundRequest refundRequest, TransactionResponse response) {

		refundTransaction.setStatus(TransactionWorkflowStatus.valueOf(response.getState()));
		refundTransaction.setResponseCode(response.getResponseCode());
		//refundTransaction.setParentTransactionId(UUID.fromString(refundRequest.getAuthorizationId()));
		refundTransaction.setPaymentNetworkResponseCode(response.getPaymentNetworkResponseCode());
		refundTransaction.setTraceabilityCode(response.getTraceabilityCode());
		return transactionRepository.save(refundTransaction);
	}
}
