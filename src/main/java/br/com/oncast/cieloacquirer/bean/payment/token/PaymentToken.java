package br.com.oncast.cieloacquirer.bean.payment.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.cieloacquirer.bean.payment.PaymentSource;
import br.com.oncast.cieloacquirer.bean.payment.creditcard.CardFlag;

import com.google.common.base.Objects;

@XmlRootElement(name = "dados-portador")
@XmlAccessorType(XmlAccessType.NONE)
public class PaymentToken implements PaymentSource {

	private static final long serialVersionUID = 1L;

	private TokenData tokenData;

	private CardFlag cardFlag;

	PaymentToken() {}

	public PaymentToken(final TokenData tokenData, final CardFlag creditCardFlag) {
		this.tokenData = tokenData;
		this.cardFlag = creditCardFlag;
	}

	public PaymentToken(final String tokenCode, final CardFlag flag) {
		this.tokenData = new TokenData(tokenCode);
		this.cardFlag = flag;
	}

	@XmlElement(name = "token")
	public String getToken() {
		return tokenData.getCode();
	}

	public TokenStatus getStatus() {
		return tokenData.getStatus();
	}

	public String getTruncatedCardNumber() {
		return tokenData.getTruncatedCardNumber();
	}

	public CardFlag getCardFlag() {
		return cardFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final PaymentToken other = (PaymentToken) obj;
		if (getToken() == null) {
			if (other.getToken() != null) return false;
		} else if (!getToken().equals(other.getToken())) return false;
		return true;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("tokenData", tokenData)
				.add("creditCardFlag", cardFlag)
				.toString();
	}

	public String getCardFinalNumbers() {
		final String number = getTruncatedCardNumber();
		return number.substring(number.length() - 4);
	}

}
