package pagos.payu.infrastructure;

import lombok.extern.slf4j.Slf4j;
import pagos.payu.infrastructure.dto.authorization.AuthorizationRequest;
import pagos.payu.core.model.common.CardholderData;
import pagos.payu.core.model.common.CreditCard;
import pagos.payu.core.model.common.Payer;
import pagos.payu.core.model.common.PaymentCard;
import pagos.payu.core.model.common.Person;
import pagos.payu.core.model.common.Transaction;
import pagos.payu.core.model.common.TransactionType;
import pagos.payu.core.provider.TransactionProvider;

@Slf4j
public class TransactionProviderImpl implements TransactionProvider {

	//todo create repository Transaction

	@Override
	public Transaction findTransaction(final AuthorizationRequest authorizationRequest) {

		//todo create repository.findTransaction();

		return null;
	}

	@Override
	public Transaction toTransaction(final AuthorizationRequest authorizationRequest) {

		if (authorizationRequest == null) {
			return null;
		}

		final Transaction transaction = new Transaction();
		transaction.setCreditCard(toCreditCard(authorizationRequest.getPaymentCard()));
		transaction.setMerchantId(authorizationRequest.getMerchantId());
		transaction.setPaymentMethodMainName(authorizationRequest.getPaymentMethod());
		transaction.setType(
				authorizationRequest.isCapture() ? TransactionType.AUTHORIZATION_AND_CAPTURE : TransactionType.AUTHORIZATION);
		transaction.setExpirationDate(authorizationRequest.getExpirationDate());

		return transaction;
	}

	public CreditCard toCreditCard(final PaymentCard paymentCard) {

		if (paymentCard == null) {
			return null;
		}

		final CardholderData cardholderData = new CardholderData();
		cardholderData.setCardholderName(paymentCard.getCardHolderName());
		cardholderData.setPan(paymentCard.getNumber());
		cardholderData.setExpirationDate(
				String.format("20%02d/%02d", paymentCard.getExpYear(), paymentCard.getExpMonth()));
		cardholderData.setVerificationCode(paymentCard.getSecurityCode());
		cardholderData.setToken(paymentCard.getToken());

		final CreditCard creditCard = new CreditCard();
		// todo complete card holder data -> creditCard.setCardholderData(cardholderData);
		creditCard.setIssuerBank(paymentCard.getIssuerBank());
		creditCard.setProcessWithoutCvv2(paymentCard.isProcessWithoutCvv2());
		return creditCard;

	}

	public Payer toPayer(final Person payer) {

		if (payer == null) {
			return null;
		}

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
	}
}
