package pagos.payu.configuration.usecases;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;
import pagos.payu.core.UseCaseAuthorization;
import pagos.payu.core.UseCaseCapture;
import pagos.payu.core.impl.UseCaseAuthorizationImpl;
import pagos.payu.core.impl.UseCaseCaptureImpl;
import pagos.payu.core.provider.AuthorizationProvider;
import pagos.payu.core.provider.CaptureProvider;
import pagos.payu.core.provider.OrderProvider;
import pagos.payu.core.provider.TransactionProvider;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "conciliation.report.bank")
@ComponentScan("pagos.payu")
public class UseCasesSetup {

	@Bean
	public UseCaseAuthorization makeUseCaseAuthorization(final AuthorizationProvider authorizationProvider,
														 final TransactionProvider transactionProvider, final OrderProvider orderProvider) {

		return new UseCaseAuthorizationImpl(authorizationProvider, transactionProvider, orderProvider);
	}

	@Bean
	public UseCaseCapture makeUseCaseCapture(final CaptureProvider captureProvider) {

		return new UseCaseCaptureImpl(captureProvider);
	}

	//TODO: create use case Refund
}
