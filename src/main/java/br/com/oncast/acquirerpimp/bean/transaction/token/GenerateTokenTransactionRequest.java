package br.com.oncast.acquirerpimp.bean.transaction.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.acquirerpimp.bean.establishment.Establishment;
import br.com.oncast.acquirerpimp.bean.payment.creditcard.PaymentCard;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTransactionRequest;

@XmlRootElement(name = "requisicao-token")
@XmlAccessorType(XmlAccessType.NONE)
public class GenerateTokenTransactionRequest implements CieloTransactionRequest<GenerateTokenTransactionResponse> {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "dados-ec")
	private Establishment establishment;

	@XmlElement(name = "dados-portador")
	private PaymentCard creditCard;

	@XmlAttribute(name = "id")
	private String id;

	@XmlAttribute(name = "versao")
	private String version;

	protected GenerateTokenTransactionRequest() {}

	public GenerateTokenTransactionRequest(PaymentCard creditCard) {
		this.id = new String("8fc889c7-004f-42f1-963a-31aa26f75e5c");
		this.version = "1.2.1";
		this.creditCard = creditCard;
	}

	public Class<GenerateTokenTransactionResponse> getResponseType() {
		return GenerateTokenTransactionResponse.class;
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public PaymentCard getCreditCard() {
		return creditCard;
	}

	public CieloTransactionRequest<GenerateTokenTransactionResponse> setEstablishment(Establishment establishment) {
		this.establishment = establishment;
		return this;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
