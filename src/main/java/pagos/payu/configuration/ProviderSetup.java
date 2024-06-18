package pagos.payu.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pagos.payu.core.provider.AuthorizationProvider;
import pagos.payu.core.provider.CaptureProvider;
import pagos.payu.core.provider.OrderProvider;
import pagos.payu.core.provider.TransactionProvider;
import pagos.payu.infrastructure.AuthorizationProviderImpl;
import pagos.payu.infrastructure.CaptureProviderImpl;
import pagos.payu.infrastructure.OrderProviderImpl;
import pagos.payu.infrastructure.TransactionProviderImpl;

@Configuration
//@EnableJpaRepositories("pagos.payu.infrastructure.repository")
@EntityScan({"pagos.payu.infrastructure.database.model", "pagos.payu.core.model"})
public class ProviderSetup {

	@Bean
	public AuthorizationProvider authorizationProvider() {

		//todo return new AuthorizationProviderImpl(transactionRepository);
		return new AuthorizationProviderImpl();
	}

	@Bean
	public TransactionProvider transactionProvider() {

		//todo return new AuthorizationProviderImpl(transactionRepository);
		return new TransactionProviderImpl();
	}

	@Bean
	public OrderProvider orderProvider() {

		//todo return new AuthorizationProviderImpl(transactionRepository);
		return new OrderProviderImpl();
	}

	@Bean
	public CaptureProvider captureProvider() {

		//todo add repository
		return new CaptureProviderImpl();
	}


	//Todo Create Refund Provider
}
