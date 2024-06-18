package pagos.payu.core.provider;

import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.entity.CreditCard;
import pagos.payu.core.model.common.Payer;
import pagos.payu.core.model.common.PaymentCard;
import pagos.payu.core.model.common.Person;
import pagos.payu.core.model.entity.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransactionProvider {

	Optional<Transaction> findTransaction(UUID authorizationId);

	Transaction saveTransaction(Transaction transaction);

	Transaction toTransaction(AuthorizationRequest authorizationRequest);

	CreditCard toCreditCard(final PaymentCard paymentCard);

	Payer toPayer(final Person payer);

}
