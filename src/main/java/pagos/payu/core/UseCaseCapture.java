package pagos.payu.core;

import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.core.model.common.TransactionResponse;

public interface UseCaseCapture {

	TransactionResponse processRequest(Integer orderId, String authorizationId, CaptureRequest captureRequest);
}
