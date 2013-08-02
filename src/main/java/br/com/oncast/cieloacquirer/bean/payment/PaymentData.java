package br.com.oncast.cieloacquirer.bean.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import br.com.oncast.cieloacquirer.bean.payment.creditcard.CardFlag;

import com.google.common.base.Objects;

@XmlType(name = "forma-pagamento")
@XmlAccessorType(XmlAccessType.NONE)
public class PaymentData implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "bandeira")
	private CardFlag flag;

	@XmlElement(name = "produto")
	private PaymentType type;

	@XmlElement(name = "parcelas")
	private int installment;

	PaymentData() {}

	public PaymentData(final CardFlag flag) {
		this.flag = flag;
		this.type = PaymentType.CREDIT;
		this.installment = 1;
	}

	public PaymentData setType(final PaymentType type) {
		this.type = type;
		return this;
	}

	public PaymentData setInstallment(final int installment) {
		this.installment = installment;
		return this;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("flag", flag)
				.add("type", type)
				.add("installment", installment)
				.toString();
	}

}
