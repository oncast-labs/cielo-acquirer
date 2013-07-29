package br.com.oncast.acquirerpimp.bean.transaction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.acquirerpimp.bean.creditcard.CreditCard;
import br.com.oncast.acquirerpimp.bean.establishment.Establishment;

@XmlRootElement(name = "requisicao-token")
@XmlAccessorType(XmlAccessType.NONE)
public class CieloTokenRequestTransaction implements CieloTransaction<CieloTokenRequestResponse> {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "dados-ec")
	private Establishment establishment;

	@XmlElement(name = "dados-portador")
	private CreditCard creditCard;

	@XmlAttribute(name = "id")
	private String id;

	@XmlAttribute(name = "versao")
	private String version;

	protected CieloTokenRequestTransaction() {}

	public CieloTokenRequestTransaction(CreditCard creditCard) {
		this.id = new String("6560a94c-663b-4aec-9a45-e45f278e00b4");
		this.version = "1.2.1";
		this.creditCard = creditCard;
	}

	public ResponseBuilder<CieloTokenRequestResponse> getResponseBuilder() {
		return new ResponseBuilder<CieloTokenRequestResponse>() {
			public CieloTokenRequestResponse fromXmlString(String xml) {
				return new CieloTokenRequestResponse();
			}
		};
	}

	public Establishment getEstablishment() {
		return establishment;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public CieloTransaction<CieloTokenRequestResponse> setEstablishment(Establishment establishment) {
		this.establishment = establishment;
		return this;
	}

}
