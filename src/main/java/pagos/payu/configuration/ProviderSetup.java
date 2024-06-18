package pagos.payu.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pagos.payu.application.caseuses.UseCaseAuthorization;
import pagos.payu.application.caseuses.UseCaseCapture;
import pagos.payu.application.caseuses.UseCaseRefund;
import pagos.payu.application.caseuses.impl.UseCaseRefundImpl;
import pagos.payu.core.provider.CreditCardProvider;
import pagos.payu.core.provider.OrderProvider;
import pagos.payu.core.provider.TransactionProvider;
import pagos.payu.application.caseuses.impl.UseCaseAuthorizationImpl;
import pagos.payu.application.caseuses.impl.UseCaseCaptureImpl;
import pagos.payu.infrastructure.CreditCardProviderImpl;
import pagos.payu.infrastructure.OrderProviderImpl;
import pagos.payu.infrastructure.TransactionProviderImpl;
import pagos.payu.infrastructure.repository.CreditCardRepository;
import pagos.payu.infrastructure.repository.OrderRepository;
import pagos.payu.infrastructure.repository.TransactionRepository;

@Configuration
//@EnableJpaRepositories("pagos.payu.infrastructure.repository")
@EntityScan({"pagos.payu.infrastructure.database.model", "pagos.payu.core.model"})
public class ProviderSetup {

	@Bean
	public UseCaseAuthorization authorizationProvider(TransactionProvider transactionProvider, OrderProvider orderProvider, CreditCardProvider creditCardProvider) {

		//todo return new AuthorizationProviderImpl(transactionRepository);
		return new UseCaseAuthorizationImpl(transactionProvider, orderProvider, creditCardProvider);
	}

	@Bean
	public TransactionProvider transactionProvider(TransactionRepository transactionRepository) {

		//todo return new AuthorizationProviderImpl(transactionRepository);
		return new TransactionProviderImpl(transactionRepository);
	}

	@Bean
	public OrderProvider orderProvider(OrderRepository orderRepository) {

		//todo return new AuthorizationProviderImpl(transactionRepository);
		return new OrderProviderImpl(orderRepository);
	}

	@Bean
	public UseCaseCapture captureProvider() {

		//todo add repository
		return new UseCaseCaptureImpl();
	}


	//Todo Create Refund Provider
	@Bean
	public UseCaseRefund refundProvider(){
		return new UseCaseRefundImpl();
	}

	@Bean
	public CreditCardProvider creditCardProvider(CreditCardRepository creditCardRepository){
		return new CreditCardProviderImpl(creditCardRepository);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
