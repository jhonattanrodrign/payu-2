package pagos.payu.infrastructure;

import java.util.Date;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagos.payu.application.exceptions.InvalidAuthorizationRequestException;
import pagos.payu.infrastructure.dto.OrderState;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.entity.Order;
import pagos.payu.core.provider.OrderProvider;
import pagos.payu.infrastructure.repository.OrderRepository;

@Slf4j
public class OrderProviderImpl implements OrderProvider {


	private OrderRepository orderRepository;

	private static final Logger logger = LoggerFactory.getLogger(OrderProviderImpl.class);

	public OrderProviderImpl(OrderRepository orderRepository) {
		this.orderRepository= orderRepository;
	}

	@Override
	public Optional<Order> findOrder(final AuthorizationRequest authorizationRequest) {

		try {
			return orderRepository.findById(authorizationRequest.getOrderId());
		} catch (Exception e) {
			logger.error("Error finding order with id: " + authorizationRequest.getOrderId(), e);
			throw new RuntimeException("Error finding order", e);
		}
	}

	@Override
	public Order saveOrder(Order order) {
		try{
			return orderRepository.save(order);
		} catch (Exception e) {
			logger.error("Error saving order", e);
			throw new RuntimeException("Error saving order", e);
		}
	}

	@Override
	public Order toOrder(final AuthorizationRequest authorizationRequest) {

		if (authorizationRequest == null) {
			logger.error("Authorization request is null");
			throw new InvalidAuthorizationRequestException("Authorization request cannot be null");
		}

		try {
			final Order order = new Order();
			order.setAccountId(authorizationRequest.getAccountId());
			order.setMerchantId(authorizationRequest.getMerchantId());
			order.setOrderId(authorizationRequest.getOrderId());
			order.setAmount(authorizationRequest.getAmount().getTotal());
			order.setCreationDate(new Date());
			order.setState(OrderState.NEW.getValue());

			return order;
		}catch (Exception e){
			logger.error("Error converting AuthorizationRequest to Order", e);
			throw new RuntimeException("Error converting AuthorizationRequest to Order", e);
		}
	}
}
