package pagos.payu.configuration.usecases;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;
import pagos.payu.application.caseuses.UseCaseRefund;
import pagos.payu.application.services.RefundServiceImpl;
import pagos.payu.core.services.AuthorizationService;
import pagos.payu.core.services.CaptureService;
import pagos.payu.application.services.AuthorizationServiceImpl;
import pagos.payu.application.services.CaptureServiceImpl;
import pagos.payu.application.caseuses.UseCaseAuthorization;
import pagos.payu.application.caseuses.UseCaseCapture;
import pagos.payu.core.provider.OrderProvider;
import pagos.payu.core.provider.TransactionProvider;
import pagos.payu.core.services.RefundService;
import pagos.payu.infrastructure.repository.TransactionRepository;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "conciliation.report.bank")
@ComponentScan("pagos.payu")
public class UseCasesSetup {

	@Bean
	public AuthorizationService makeUseCaseAuthorization(final UseCaseAuthorization useCaseAuthorization,
														 final TransactionProvider transactionProvider, final OrderProvider orderProvider) {

		return new AuthorizationServiceImpl(useCaseAuthorization, transactionProvider, orderProvider);
	}

	@Bean
	public CaptureService makeUseCaseCapture(final UseCaseCapture useCaseCapture, final TransactionRepository transactionRepository) {

		return new CaptureServiceImpl(useCaseCapture,transactionRepository);
	}

	//TODO: create use case Refund
	//@Bean
	//public RefundService makeUseCaseRefund(final UseCaseRefund useCaseRefund, final TransactionRepository transactionRepository) {

	//	return new RefundServiceImpl(useCaseRefund,transactionRepository);
	//}
}
