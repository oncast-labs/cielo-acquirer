package br.com.oncast.cieloacquirer.acquirer;

import java.net.URL;

import br.com.oncast.cieloacquirer.CieloAcquirerModule;
import br.com.oncast.cieloacquirer.acquirer.configuration.CieloAcquirerConfiguration;
import br.com.oncast.cieloacquirer.bean.establishment.Establishment;
import br.com.oncast.cieloacquirer.bean.payment.OrderData;
import br.com.oncast.cieloacquirer.bean.payment.PaymentData;
import br.com.oncast.cieloacquirer.bean.payment.PaymentSource;
import br.com.oncast.cieloacquirer.bean.payment.creditcard.PaymentCard;
import br.com.oncast.cieloacquirer.bean.payment.token.PaymentToken;
import br.com.oncast.cieloacquirer.bean.transaction.CieloTransactionException;
import br.com.oncast.cieloacquirer.bean.transaction.payment.AuthorizationType;
import br.com.oncast.cieloacquirer.bean.transaction.payment.PaymentTransactionRequest;
import br.com.oncast.cieloacquirer.bean.transaction.payment.PaymentTransactionResponse;
import br.com.oncast.cieloacquirer.bean.transaction.token.GenerateTokenTransactionRequest;
import br.com.oncast.cieloacquirer.bean.transaction.token.GenerateTokenTransactionResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CieloAcquirer {

	private final CieloTransactionSender transactionSender;

	private final Establishment establishment;

	private final URL returnUrl;

	private static final TransactionListener NULL_TRANSACTION_LISTENER = new TransactionListener() {
		public void onTransactionSent(final String sentRequestXml, final String receivedResponseXml) {}
	};

	@Inject
	CieloAcquirer(final CieloAcquirerConfiguration configuration, final CieloTransactionSender transactionSender) {
		this.transactionSender = transactionSender;
		this.establishment = configuration.getEstablishment();
		this.returnUrl = configuration.getReturnUrl();
	}

	public static CieloAcquirer getInstance() {
		return CieloAcquirerModule.getInjector().getInstance(CieloAcquirer.class);
	}

	public PaymentToken generateToken(final PaymentCard creditCard) throws CieloTransactionException {
		return generateToken(creditCard, NULL_TRANSACTION_LISTENER);
	}

	public PaymentToken generateToken(final PaymentCard creditCard, final TransactionListener listener) throws CieloTransactionException {
		final GenerateTokenTransactionRequest request = new GenerateTokenTransactionRequest(creditCard);
		request.setEstablishment(establishment);
		final GenerateTokenTransactionResponse response = transactionSender.send(request, listener);
		return new PaymentToken(response.getTokenData(), creditCard.getCardFlag());
	}

	public PaymentTransactionResponse charge(final PaymentSource paymentSource, final int value) throws CieloTransactionException {
		return charge(paymentSource, value, NULL_TRANSACTION_LISTENER);
	}

	public PaymentTransactionResponse charge(final PaymentSource paymentSource, final int value, final TransactionListener listener) throws CieloTransactionException {
		final OrderData order = new OrderData(value);
		final PaymentData payment = new PaymentData(paymentSource.getCardFlag());
		final PaymentTransactionRequest request = new PaymentTransactionRequest(paymentSource, order, payment, AuthorizationType.RECURRING)
				.setAutoCaptureIfAuthorized(true);
		request.setEstablishment(establishment);
		request.setReturnUrl(returnUrl);
		final PaymentTransactionResponse response = transactionSender.send(request, listener);
		return response;
	}

}
