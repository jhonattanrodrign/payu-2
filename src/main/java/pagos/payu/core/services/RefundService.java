package pagos.payu.core.services;

import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.infrastructure.dto.refund.RefundRequest;

public interface RefundService {

	TransactionResponse processRequest(Integer orderId,String captureId, RefundRequest refundRequest) throws Exception;

}
