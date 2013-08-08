package br.com.oncast.cieloacquirer;

import br.com.oncast.cieloacquirer.acquirer.CieloAcquirer;
import br.com.oncast.cieloacquirer.bean.payment.creditcard.CardFlag;
import br.com.oncast.cieloacquirer.bean.payment.creditcard.PaymentCard;
import br.com.oncast.cieloacquirer.bean.payment.token.PaymentToken;
import br.com.oncast.cieloacquirer.bean.transaction.CieloTransactionException;
import br.com.oncast.cieloacquirer.bean.transaction.payment.PaymentTransactionResponse;

public class HelloWorld {

	public static void main(final String[] args) {
		final PaymentCard creditCard = new PaymentCard(4012001037141112L, 201805, "FULANO DA SILVA", CardFlag.VISA);
		creditCard.setSecurityCode(123);

		final CieloAcquirer acquirer = CieloAcquirer.getInstance();

		try {
			final PaymentTransactionResponse response = acquirer.charge(creditCard, 100);
			System.out.println(response);
		} catch (final CieloTransactionException e) {
			e.printStackTrace();
		}

		try {
			final PaymentToken token = acquirer.generateToken(creditCard);
			System.out.println(token);
			final PaymentTransactionResponse response = acquirer.charge(token, 100);
			System.out.println(response);
		} catch (final CieloTransactionException e) {
			e.printStackTrace();
		}
	}

}
