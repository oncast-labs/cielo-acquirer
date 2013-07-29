package br.com.oncast.acquirerpimp.bean.token;

import br.com.oncast.acquirerpimp.bean.creditcard.CreditCardFlag;

public class CieloToken {

	private final String token;

	private final CieloTokenStatus unblocked;

	private final CreditCardFlag creditCardFlag;

	public CieloToken(String token, CieloTokenStatus unblocked, CreditCardFlag creditCardFlag) {
		this.token = token;
		this.unblocked = unblocked;
		this.creditCardFlag = creditCardFlag;
	}

	public String getToken() {
		return token;
	}

	public CieloTokenStatus getUnblocked() {
		return unblocked;
	}

	public CreditCardFlag getCreditCardFlag() {
		return creditCardFlag;
	}

}
