package br.com.oncast.cieloacquirer.bean.payment.creditcard;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.cieloacquirer.bean.payment.PaymentSource;

@XmlRootElement(name = "dados-portador")
@XmlAccessorType(XmlAccessType.NONE)
public class PaymentCard implements PaymentSource {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "numero")
	private long cardNumber;

	@XmlElement(name = "validade")
	private int expiryDate;

	@XmlElement(name = "indicador", required = false)
	private SecurityCodeIndicator indicator;

	@XmlElement(name = "codigo-seguranca", required = false)
	private Integer securityCode;

	@XmlElement(name = "nome-portador", required = false)
	private String cardHolderName;

	private CardFlag flag;

	PaymentCard() {}

	public PaymentCard(final long cardNumber, final int expiryDate, final String cardHolderName, final CardFlag flag) {
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cardHolderName = cardHolderName;
		this.flag = flag;
		this.indicator = SecurityCodeIndicator.INEXISTENT;
	}

	public PaymentCard setSecurityCode(final int securityCode) {
		this.securityCode = securityCode;
		this.indicator = SecurityCodeIndicator.INFORMED;
		return this;
	}

	public PaymentCard setSecurityCodeIndicator(final SecurityCodeIndicator indicator) {
		assert indicator != SecurityCodeIndicator.INFORMED : "should use setSecurityCode(int code)";

		this.indicator = indicator;
		return this;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public int getExpiryDate() {
		return expiryDate;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public CardFlag getCardFlag() {
		return flag;
	}

}
