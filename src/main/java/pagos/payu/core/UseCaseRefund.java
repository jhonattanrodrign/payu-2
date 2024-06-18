package pagos.payu.core;

import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.infrastructure.dto.refund.RefundRequest;

public interface UseCaseRefund {

	TransactionResponse processRequest(Integer orderId,String captureId, RefundRequest refundRequest);

}
