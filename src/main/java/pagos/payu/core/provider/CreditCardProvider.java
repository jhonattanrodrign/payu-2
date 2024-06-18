package pagos.payu.core.provider;

import pagos.payu.core.model.entity.CreditCard;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardProvider {

    Optional<CreditCard> findCreditCard(UUID id);

    CreditCard saveCreditCard(CreditCard creditCard);

    CreditCard toCreditCard(AuthorizationRequest authorizationRequest);
}
