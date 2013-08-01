package br.com.oncast.acquirerpimp.bean.payment;

import java.io.Serializable;

import br.com.oncast.acquirerpimp.bean.payment.creditcard.CardFlag;

public interface PaymentSource extends Serializable {

	CardFlag getCardFlag();

}
