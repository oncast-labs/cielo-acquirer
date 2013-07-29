package br.com.oncast.acquirerpimp;

import br.com.oncast.acquirerpimp.acquirer.CieloAcquirer;
import br.com.oncast.acquirerpimp.bean.creditcard.CreditCard;
import br.com.oncast.acquirerpimp.bean.creditcard.CreditCardFlag;

public class Main {

	public static void main(String[] args) {
		CreditCard creditCard = new CreditCard(4012001037141112L, 201805, "FULANO DA SILVA", CreditCardFlag.VISA);
		creditCard.setSecurityCode(123);
		CieloAcquirer acquirer = CieloAcquirer.getInstance();
		acquirer.generateToken(creditCard);
		acquirer.charge(creditCard, 100);
	}

}
