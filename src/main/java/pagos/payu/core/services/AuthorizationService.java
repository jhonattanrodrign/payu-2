package pagos.payu.core.services;

import pagos.payu.core.model.common.AntiFraudPreValidationResponse;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.entity.Order;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.core.model.common.TransactionResponse;

import java.util.concurrent.ExecutionException;

public interface AuthorizationService {

	TransactionResponse processAuthorizationRequest(Order order, Transaction transaction, AuthorizationRequest authorizationRequest, AntiFraudPreValidationResponse fraudResponse) throws ExecutionException;

	Order toOrder(AuthorizationRequest authorizationRequest);

	Transaction toTransaction(AuthorizationRequest authorizationRequest);

}
