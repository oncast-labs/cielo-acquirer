package br.com.oncast.cieloacquirer.bean.payment.token;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "token")
@XmlAccessorType(XmlAccessType.NONE)
public class TokenDataWrapper implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "dados-token")
	private TokenData token;

	private TokenDataWrapper() {}

	public TokenData getTokenData() {
		return token;
	}

}
