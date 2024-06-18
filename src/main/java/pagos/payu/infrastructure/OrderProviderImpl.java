package pagos.payu.infrastructure;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.common.Order;
import pagos.payu.core.provider.OrderProvider;

@Slf4j
public class OrderProviderImpl implements OrderProvider {

	//todo create repository

	@Override
	public Order findOrder(final AuthorizationRequest authorizationRequest) {

		//todo return repository.findOrder();
		return null;
	}

	@Override
	public Order toOrder(final AuthorizationRequest authorizationRequest) {

		if (authorizationRequest == null) {
			return null;
		}

		final Order order = new Order();
		order.setAccountId(authorizationRequest.getAccountId());
		order.setMerchantId(authorizationRequest.getMerchantId());
		order.setOrderId(authorizationRequest.getOrderId());
		//todo order.setAmount(authorizationRequest.getAmount());
		order.setCreationDate(new Date());
		//todo order.setState(OrderState.READY);
		//todo order.setStatus(OrderStatus.NEW);

		return order;
	}
}
