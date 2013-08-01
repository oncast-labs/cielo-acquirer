package br.com.oncast.acquirerpimp.bean.transaction.token;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.acquirerpimp.bean.payment.token.TokenData;
import br.com.oncast.acquirerpimp.bean.payment.token.TokenDataWrapper;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTransactionResponse;

@XmlRootElement(name = "retorno-token")
@XmlAccessorType(XmlAccessType.NONE)
public class GenerateTokenTransactionResponse implements CieloTransactionResponse {

	private static final long serialVersionUID = 1L;

	@XmlAttribute(name = "id")
	private String id;

	@XmlAttribute(name = "versao")
	private String version;

	@XmlElement(name = "token")
	private TokenDataWrapper tokenDataWrapper;

	private GenerateTokenTransactionResponse() {}

	public TokenData getTokenData() {
		return tokenDataWrapper.getTokenData();
	}

}
