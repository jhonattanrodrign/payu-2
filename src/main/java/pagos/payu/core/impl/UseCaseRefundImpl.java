package pagos.payu.core.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pagos.payu.core.UseCaseRefund;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.infrastructure.dto.refund.RefundRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class UseCaseRefundImpl implements UseCaseRefund {

	@Override
	public TransactionResponse processRequest(final Integer orderId, final String captureId, final RefundRequest refundRequest) {

		return null;
	}
}
