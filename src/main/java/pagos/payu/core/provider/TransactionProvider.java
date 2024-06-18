package pagos.payu.core.provider;

import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.common.CreditCard;
import pagos.payu.core.model.common.Payer;
import pagos.payu.core.model.common.PaymentCard;
import pagos.payu.core.model.common.Person;
import pagos.payu.core.model.common.Transaction;

public interface TransactionProvider {

	Transaction findTransaction(AuthorizationRequest authorizationRequest);

	Transaction toTransaction(AuthorizationRequest authorizationRequest);

	CreditCard toCreditCard(final PaymentCard paymentCard);

	Payer toPayer(final Person payer);

}
