package pagos.payu.application.caseuses;

import pagos.payu.core.model.common.AntiFraudPreValidationResponse;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.entity.Order;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.core.model.common.TransactionResponse;

import java.util.concurrent.ExecutionException;

public interface UseCaseAuthorization {

	Transaction buildTransactionByAuthorizationRequest(Order order, Transaction transaction, AuthorizationRequest authorizationRequest, AntiFraudPreValidationResponse fraudResponse);

	TransactionResponse processAuthorizationRequest(Order order, Transaction transaction, AuthorizationRequest authorizationRequest) throws ExecutionException;

}
