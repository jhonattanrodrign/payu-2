package pagos.payu.application.caseuses;

import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.infrastructure.dto.refund.RefundRequest;

public interface UseCaseRefund {
    TransactionResponse processRequest(Transaction refundTransaction, RefundRequest refundRequest);
}
