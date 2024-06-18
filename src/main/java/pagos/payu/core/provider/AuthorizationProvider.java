package pagos.payu.core.provider;

import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.common.Order;
import pagos.payu.core.model.common.Transaction;
import pagos.payu.core.model.common.TransactionResponse;

public interface AuthorizationProvider {

	TransactionResponse processAuthorizationRequest(Order order, Transaction transaction, AuthorizationRequest authorizationRequest);

}
