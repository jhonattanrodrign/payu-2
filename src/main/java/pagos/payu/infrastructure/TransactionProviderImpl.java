package pagos.payu.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pagos.payu.application.exceptions.InvalidAuthorizationRequestException;
import pagos.payu.core.model.common.*;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.entity.CardholderData;
import pagos.payu.core.model.entity.CreditCard;
import pagos.payu.core.model.entity.Transaction;

import java.util.Optional;
import java.util.UUID;

import pagos.payu.core.provider.TransactionProvider;
import pagos.payu.infrastructure.repository.TransactionRepository;

import java.util.Date;

@Slf4j
@Service
public class TransactionProviderImpl implements TransactionProvider {

	private final TransactionRepository transactionRepository;

	private static final Logger logger = LoggerFactory.getLogger(TransactionProviderImpl.class);

	@Autowired
	public TransactionProviderImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	public Optional<Transaction> findTransaction(final UUID authorizationId) {

		try {
			return transactionRepository.findById(UUID.fromString(authorizationId.toString()));
		}catch (Exception e) {
			logger.error("Error finding transaction with id: " + authorizationId, e);
			throw new RuntimeException("Error finding transaction", e);
		}
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		try {
			return transactionRepository.save(transaction);
		} catch (Exception e) {
		logger.error("Error saving Transaction", e);
		throw new RuntimeException("Error saving transaction", e);
	}
	}

	@Override
	public Transaction toTransaction(final AuthorizationRequest authorizationRequest) {

		if (authorizationRequest == null) {
			logger.error("Authorization request is null");
			throw new InvalidAuthorizationRequestException("Authorization request cannot be null");
		}

		try {
			final Transaction transaction = new Transaction();
			transaction.setTransactionId(UUID.randomUUID());
			transaction.setExpirationDate(new Date());
			transaction.setOrderId(authorizationRequest.getOrderId());
			//transaction.setCreditCard(toCreditCard(authorizationRequest.getPaymentCard()));
			transaction.setMerchantId(authorizationRequest.getMerchantId());
			transaction.setPaymentMethodMainName(authorizationRequest.getPaymentMethod());
			transaction.setPaymentMethod(authorizationRequest.getPaymentMethod());
			transaction.setLastUpdatedDate(new Date());
			transaction.setCreationDate(new Date());
			transaction.setStatus(TransactionWorkflowStatus.NEW);
			transaction.setPaymentMethodMainName(authorizationRequest.getPaymentMethod());
			transaction.setIsPaymentAttempt(authorizationRequest.isCapture());
			transaction.setType(
					authorizationRequest.isCapture() ? TransactionType.AUTHORIZATION_AND_CAPTURE : TransactionType.AUTHORIZATION);
			transaction.setExpirationDate(authorizationRequest.getExpirationDate());
			transaction.setCurrency(authorizationRequest.getAmount().getCurrency());
			transaction.setTotal(authorizationRequest.getAmount().getTotal());
			return transaction;
		}catch (Exception e){
			logger.error("Error converting AuthorizationRequest to Transaction", e);
			throw new RuntimeException("Error converting AuthorizationRequest to Transaction", e);
		}
	}

	public CreditCard toCreditCard(final PaymentCard paymentCard) {

		if (paymentCard == null) {
			logger.error("Payment card is null");
			throw new InvalidAuthorizationRequestException("Payment card cannot be null");
		}

		try {
			final CardholderData cardholderData = new CardholderData();
			cardholderData.setCardholderName(paymentCard.getCardHolderName());
			cardholderData.setPan(paymentCard.getNumber());
			//cardholderData.setExpirationDate(
			//		String.format("20%02d/%02d", paymentCard.getExpYear(), paymentCard.getExpMonth()));
			cardholderData.setExpirationDate(paymentCard.getExpirationDate());
			cardholderData.setVerificationCode(paymentCard.getSecurityCode());
			cardholderData.setToken(paymentCard.getToken());

			final CreditCard creditCard = new CreditCard();
			// todo complete card holder data -> creditCard.setCardholderData(cardholderData);
			creditCard.setIssuerBank(paymentCard.getIssuerBank());
			creditCard.setProcessWithoutCvv2(paymentCard.isProcessWithoutCvv2());
			return creditCard;
		}catch (Exception e){
			logger.error("Error converting PaymentCard to CreditCard", e);
			throw new RuntimeException("Error converting PaymentCard to CreditCard", e);
		}

	}

	public Payer toPayer(final Person payer) {

		if (payer == null) {
			logger.error("Person is null");
			throw new InvalidAuthorizationRequestException("Payer cannot be null");
		}

		try {
			final Payer newPayer = new Payer();
			newPayer.setName(payer.getBusinessName());
			newPayer.setCnpj(payer.getCnpj());
			newPayer.setDocument(payer.getIdentificationNumber());
			newPayer.setDocumentType(payer.getIdentificationType());
			newPayer.setEmail(payer.getEmail());
			newPayer.setName(payer.getFullName());
			newPayer.setPersonType(payer.getType());
			newPayer.setPhone(payer.getPhone());
			return newPayer;
		}catch (Exception e){
			logger.error("Error converting Person to Payer", e);
			throw new RuntimeException("Error converting Person to Payer", e);
		}
	}
}
