package pagos.payu.core.provider;

import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.common.Order;

public interface OrderProvider {


	Order toOrder(AuthorizationRequest authorizationRequest);

	Order findOrder(AuthorizationRequest authorizationRequest);

}
