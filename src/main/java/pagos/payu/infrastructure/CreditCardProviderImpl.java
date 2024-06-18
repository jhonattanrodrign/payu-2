package pagos.payu.infrastructure;

import pagos.payu.application.exceptions.InvalidAuthorizationRequestException;
import pagos.payu.core.model.entity.CardholderData;
import pagos.payu.core.model.entity.CreditCard;
import pagos.payu.core.provider.CreditCardProvider;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.infrastructure.repository.CreditCardRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCardProviderImpl implements CreditCardProvider {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardProviderImpl.class);
    private CreditCardRepository creditCardRepository;

    public CreditCardProviderImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository= creditCardRepository;
    }

    @Override
    public Optional<CreditCard> findCreditCard(UUID id) {

        try {
            return creditCardRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error finding credit card with id: " + id, e);
            throw new RuntimeException("Error finding credit card", e);
        }
    }

    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        try {
            return creditCardRepository.save(creditCard);
        } catch (Exception e) {
            logger.error("Error saving credit card", e);
            throw new RuntimeException("Error saving credit card", e);
        }
    }

    @Override
    public CreditCard toCreditCard(AuthorizationRequest authorizationRequest) {
        if (authorizationRequest == null) {
            logger.error("Authorization request is null");
            throw new InvalidAuthorizationRequestException("Authorization request cannot be null");
        }
        try {
            final CreditCard creditCard= new CreditCard();
            creditCard.setId(UUID.randomUUID());
            creditCard.setCreationDate(new Date());
            CardholderData cardholderData = new CardholderData();
            cardholderData.setCardholderName(authorizationRequest.getPaymentCard().getCardHolderName());
            cardholderData.setPan(authorizationRequest.getPaymentCard().getNumber());
            cardholderData.setExpirationDate(authorizationRequest.getPaymentCard().getExpirationDate());
            creditCard.setCardHolderData(cardholderData);
            return creditCard;
        }catch (Exception e){
            logger.error("Error converting AuthorizationRequest to CreditCard", e);
            throw new RuntimeException("Error converting AuthorizationRequest to CreditCard", e);
        }
    }
}
