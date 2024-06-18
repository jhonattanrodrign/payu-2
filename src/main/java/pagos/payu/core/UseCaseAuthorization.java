package pagos.payu.core;

import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.common.Order;
import pagos.payu.core.model.common.Transaction;
import pagos.payu.core.model.common.TransactionResponse;

public interface UseCaseAuthorization {

	TransactionResponse processAuthorizationRequest(Order order, Transaction transaction, AuthorizationRequest authorizationRequest);

	Order toOrder(AuthorizationRequest authorizationRequest);

	Transaction toTransaction(AuthorizationRequest authorizationRequest);

}
