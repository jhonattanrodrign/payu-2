package pagos.payu.application.caseuses;

import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.core.model.common.TransactionResponse;

public interface UseCaseCapture {

	TransactionResponse processRequest(Transaction captureTransaction, CaptureRequest captureRequest);
}
