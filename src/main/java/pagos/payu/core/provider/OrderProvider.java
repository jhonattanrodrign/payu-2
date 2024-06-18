package pagos.payu.core.provider;

import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.entity.Order;

import java.util.Optional;

public interface OrderProvider {


	Order toOrder(AuthorizationRequest authorizationRequest);

	Optional<Order> findOrder(AuthorizationRequest authorizationRequest);

    Order saveOrder(Order order);
}
