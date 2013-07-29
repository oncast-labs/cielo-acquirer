package br.com.oncast.acquirerpimp.bean.creditcard;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRegistry
@XmlRootElement(name = "dados-portador")
@XmlType(name = "dados-portador")
@XmlAccessorType(XmlAccessType.NONE)
public class CreditCard implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "numero")
	private long cardNumber;

	@XmlElement(name = "validade")
	private int validThru;

	@XmlElement(name = "indicador")
	private SecurityCodeIndicator indicator;

	@XmlElement(name = "codigo-seguranca", required = false)
	private Integer securityCode;

	@XmlElement(name = "nome-portador")
	private String carrierName;

	private CreditCardFlag flag;

	CreditCard() {}

	public CreditCard(long cardNumber, int validThru, String carrierName, CreditCardFlag flag) {
		this.cardNumber = cardNumber;
		this.validThru = validThru;
		this.carrierName = carrierName;
		this.flag = flag;
		setSecurityCodeIndicator(SecurityCodeIndicator.INEXISTENT);
	}

	public CreditCard setSecurityCode(int securityCode) {
		setSecurityCodeIndicator(SecurityCodeIndicator.INFORMED);
		this.securityCode = securityCode;
		return this;
	}

	public CreditCard setSecurityCodeIndicator(SecurityCodeIndicator indicator) {
		this.indicator = indicator;
		return this;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public int getValidThru() {
		return validThru;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public CreditCardFlag getFlag() {
		return flag;
	}

}
