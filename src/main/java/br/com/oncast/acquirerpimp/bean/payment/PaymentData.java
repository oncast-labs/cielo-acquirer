package br.com.oncast.acquirerpimp.bean.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.acquirerpimp.bean.creditcard.CreditCardFlag;

@XmlRootElement(name = "forma-pagamento")
@XmlAccessorType(XmlAccessType.NONE)
public class PaymentData implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "bandeira")
	private CreditCardFlag flag;

	@XmlElement(name = "produto")
	private PaymentType type;

	@XmlElement(name = "parcelas")
	private int installment;

	PaymentData() {}

	public PaymentData(CreditCardFlag flag) {
		this.flag = flag;
		this.type = PaymentType.CREDIT;
		this.installment = 1;
	}

	public PaymentData setType(PaymentType type) {
		this.type = type;
		return this;
	}

	public PaymentData setInstallment(int installment) {
		this.installment = installment;
		return this;
	}

}
