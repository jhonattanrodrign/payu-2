package pagos.payu.core.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pagos.payu.core.UseCaseAuthorization;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.common.Order;
import pagos.payu.core.model.common.Transaction;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.core.provider.AuthorizationProvider;
import pagos.payu.core.provider.OrderProvider;
import pagos.payu.core.provider.TransactionProvider;

@Slf4j
@RequiredArgsConstructor
@Service
public class UseCaseAuthorizationImpl implements UseCaseAuthorization {

	private final AuthorizationProvider authorizationProvider;
	private final TransactionProvider transactionProvider;
	private final OrderProvider orderProvider;

	@Override
	public TransactionResponse processAuthorizationRequest(final Order order, final Transaction transaction, final AuthorizationRequest authorizationRequest) {

		return authorizationProvider.processAuthorizationRequest(order, transaction, authorizationRequest);
	}

	@Override
	public Order toOrder(final AuthorizationRequest authorizationRequest) {

		return orderProvider.toOrder(authorizationRequest);
	}

	@Override
	public Transaction toTransaction(final AuthorizationRequest authorizationRequest) {

		return transactionProvider.toTransaction(authorizationRequest);
	}
}
