package br.com.oncast.acquirerpimp.acquirer;

import java.net.URL;

import br.com.oncast.acquirerpimp.CieloAcquirerModule;
import br.com.oncast.acquirerpimp.acquirer.configuration.CieloAcquirerConfiguration;
import br.com.oncast.acquirerpimp.acquirer.exception.CieloTransactionException;
import br.com.oncast.acquirerpimp.bean.establishment.Establishment;
import br.com.oncast.acquirerpimp.bean.payment.OrderData;
import br.com.oncast.acquirerpimp.bean.payment.PaymentData;
import br.com.oncast.acquirerpimp.bean.payment.PaymentSource;
import br.com.oncast.acquirerpimp.bean.payment.creditcard.PaymentCard;
import br.com.oncast.acquirerpimp.bean.payment.token.PaymentToken;
import br.com.oncast.acquirerpimp.bean.transaction.payment.AuthorizationType;
import br.com.oncast.acquirerpimp.bean.transaction.payment.PaymentTransactionRequest;
import br.com.oncast.acquirerpimp.bean.transaction.payment.PaymentTransactionResponse;
import br.com.oncast.acquirerpimp.bean.transaction.token.GenerateTokenTransactionRequest;
import br.com.oncast.acquirerpimp.bean.transaction.token.GenerateTokenTransactionResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CieloAcquirer {

	private final CieloTransactionSender transactionSender;

	private final Establishment establishment;

	private final URL returnUrl;

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
		final GenerateTokenTransactionRequest request = new GenerateTokenTransactionRequest(creditCard);
		request.setEstablishment(establishment);
		final GenerateTokenTransactionResponse response = transactionSender.send(request);
		return new PaymentToken(response.getTokenData(), creditCard.getCardFlag());
	}

	public PaymentTransactionResponse charge(final PaymentSource paymentSource, final int value) throws CieloTransactionException {
		final OrderData order = new OrderData(value);
		final PaymentData payment = new PaymentData(paymentSource.getCardFlag());
		final PaymentTransactionRequest request = new PaymentTransactionRequest(paymentSource, order, payment, AuthorizationType.RECURRING)
				.setAutoCaptureWhenAuthorized(true);
		request.setEstablishment(establishment);
		request.setReturnUrl(returnUrl);
		final PaymentTransactionResponse response = transactionSender.send(request);
		return response;
	}
}
