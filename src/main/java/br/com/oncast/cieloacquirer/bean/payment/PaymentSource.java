package br.com.oncast.cieloacquirer.bean.payment;

import java.io.Serializable;

import br.com.oncast.cieloacquirer.bean.payment.creditcard.CardFlag;

public interface PaymentSource extends Serializable {

	CardFlag getCardFlag();

}
