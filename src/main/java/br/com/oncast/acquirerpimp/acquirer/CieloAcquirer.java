package br.com.oncast.acquirerpimp.acquirer;

import java.net.URI;

import br.com.oncast.acquirerpimp.CieloAcquirerModule;
import br.com.oncast.acquirerpimp.bean.creditcard.CreditCard;
import br.com.oncast.acquirerpimp.bean.payment.OrderData;
import br.com.oncast.acquirerpimp.bean.payment.PaymentData;
import br.com.oncast.acquirerpimp.bean.token.CieloToken;
import br.com.oncast.acquirerpimp.bean.token.CieloTokenStatus;
import br.com.oncast.acquirerpimp.bean.transaction.CieloPaymentTransaction;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTokenRequestTransaction;
import br.com.oncast.acquirerpimp.bean.transaction.TransactionAuthorizationType;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CieloAcquirer {

	private final CieloTransactionSender requestSender;

	@Inject
	CieloAcquirer(CieloTransactionSender requestSender) {
		this.requestSender = requestSender;
	}

	public CieloToken generateToken(CreditCard creditCard) {
		requestSender.send(new CieloTokenRequestTransaction(creditCard));
		return new CieloToken("TuS6LeBHWjqFFtE7S3zR052Jl/KUlD+tYJFpAdlA87E=", CieloTokenStatus.UNBLOCKED, creditCard.getFlag());
	}

	public static CieloAcquirer getInstance() {
		return CieloAcquirerModule.getInjector().getInstance(CieloAcquirer.class);
	}

	public void charge(CreditCard creditCard, int amount) {
		OrderData order = new OrderData(amount);
		PaymentData payment = new PaymentData(creditCard.getFlag());
		CieloPaymentTransaction transaction = new CieloPaymentTransaction(creditCard, order, payment, TransactionAuthorizationType.RECURRING);
		transaction.setReturnUrl(URI.create("http://anythinkinexistant.com.br"));
		requestSender.send(transaction);
	}
}
