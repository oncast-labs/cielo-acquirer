package br.com.oncast.acquirerpimp.bean.payment.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.acquirerpimp.bean.payment.PaymentSource;
import br.com.oncast.acquirerpimp.bean.payment.creditcard.CardFlag;

import com.google.common.base.Objects;

@XmlRootElement(name = "dados-portador")
@XmlAccessorType(XmlAccessType.NONE)
public class PaymentToken implements PaymentSource {

	private static final long serialVersionUID = 1L;

	private TokenData tokenData;

	private CardFlag creditCardFlag;

	PaymentToken() {}

	public PaymentToken(final TokenData tokenData, final CardFlag creditCardFlag) {
		this.tokenData = tokenData;
		this.creditCardFlag = creditCardFlag;
	}

	@XmlElement(name = "token")
	public String getToken() {
		return tokenData.getCode();
	}

	public TokenStatus getStatus() {
		return tokenData.getStatus();
	}

	public CardFlag getCardFlag() {
		return creditCardFlag;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("tokenData", tokenData)
				.add("creditCardFlag", creditCardFlag)
				.toString();
	}

}
