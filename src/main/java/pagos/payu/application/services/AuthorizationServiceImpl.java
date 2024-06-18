package pagos.payu.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import pagos.payu.core.model.common.AntiFraudPreValidationResponse;
import pagos.payu.core.services.AuthorizationService;
import pagos.payu.infrastructure.adapters.AcquirerNetworkService;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.entity.Order;
import pagos.payu.core.model.entity.Transaction;
import pagos.payu.core.model.common.TransactionResponse;
import pagos.payu.application.caseuses.UseCaseAuthorization;
import pagos.payu.core.provider.OrderProvider;
import pagos.payu.core.provider.TransactionProvider;
import pagos.payu.infrastructure.dto.authorization.AuthorizationResponse;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	private final UseCaseAuthorization useCaseAuthorization;
	private final TransactionProvider transactionProvider;
	private final OrderProvider orderProvider;

	@Autowired
	private RestTemplate restTemplate;

	private AcquirerNetworkService acquirerNetworkService;

	@Override
	public TransactionResponse processAuthorizationRequest(final Order order, final Transaction transaction, final AuthorizationRequest authorizationRequest, AntiFraudPreValidationResponse fraudResponse) throws ExecutionException {

		Transaction transactionToProcess = useCaseAuthorization.buildTransactionByAuthorizationRequest(order, transaction, authorizationRequest, fraudResponse);
		//enviar peticion a sendAuthorizationRequestAcquirerNetwork()
		return useCaseAuthorization.processAuthorizationRequest(order, transactionToProcess, authorizationRequest);
	}

	public TransactionResponse sendAuthorizationRequestAcquirerNetwork(final AuthorizationRequest authorizationRequest) {
		// Enviar solicitud de autorización a la red del adquirente
		AuthorizationResponse authorizationResponse = acquirerNetworkService.sendAuthorizationRequestToAcquirerNetwork(authorizationRequest);
		// Evaluar la respuesta de autorización
		return evaluateAuthorizationResponse(authorizationResponse);
	}

	private TransactionResponse evaluateAuthorizationResponse(AuthorizationResponse authorizationResponse) {
		TransactionResponse transactionResponse = new TransactionResponse();
		// Asumimos que `TransactionResponse` tiene un método para establecer el estado y detalles
		//transactionResponse.setStatus(authorizationResponse.getStatus());
		//transactionResponse.setDetails(authorizationResponse.getDetails());
		return transactionResponse;
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
