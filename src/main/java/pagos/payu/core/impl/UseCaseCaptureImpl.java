package pagos.payu.core.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pagos.payu.core.UseCaseCapture;
import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.core.model.common.Transaction;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.core.model.common.TransactionType;
import pagos.payu.core.provider.CaptureProvider;

@Slf4j
@RequiredArgsConstructor
@Service
public class UseCaseCaptureImpl implements UseCaseCapture {

	private final CaptureProvider captureProvider;

	//todo create repository

	@Override
	public TransactionResponse processRequest(final Integer orderId, final String authorizationId,final CaptureRequest captureRequest) {

		//todo find authorization with repository
		Transaction authorization = getAuthorization(orderId, authorizationId, captureRequest);

		Transaction captureTransaction = convertTransaction(captureRequest,authorization);

		return captureProvider.processRequest(captureTransaction, captureRequest);
	}

	private Transaction convertTransaction(final CaptureRequest captureRequest, final Transaction authorization) {

		authorization.setOrderId(captureRequest.getOrderId());
		authorization.setType(TransactionType.CAPTURE);
		authorization.setTransactionId("d348809f-2162-4da9-9c9d-4d6399c92772");
		return authorization;
	}

	private Transaction getAuthorization(final Integer orderId, final String authorizationId, final CaptureRequest captureRequest) {

		return new Transaction();
	}
}
