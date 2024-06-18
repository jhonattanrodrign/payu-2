package pagos.payu.core.provider;

import pagos.payu.infrastructure.dto.capture.CaptureRequest;
import pagos.payu.core.model.common.Transaction;
import pagos.payu.core.model.common.TransactionResponse;

public interface CaptureProvider {

	TransactionResponse processRequest(Transaction captureTransaction, CaptureRequest captureRequest);
}
