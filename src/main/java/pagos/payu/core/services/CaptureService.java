package pagos.payu.core.services;

import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.core.model.common.TransactionResponse;

public interface CaptureService {

	TransactionResponse processRequest(Integer orderId, String authorizationId, CaptureRequest captureRequest) throws Exception;
}
