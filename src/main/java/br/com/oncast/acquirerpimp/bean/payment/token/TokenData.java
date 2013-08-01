package br.com.oncast.acquirerpimp.bean.payment.token;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.common.base.Objects;

@XmlType(name = "dados-token")
@XmlAccessorType(XmlAccessType.NONE)
public class TokenData implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "codigo-token")
	private String code;

	@XmlElement(name = "status")
	private TokenStatus status;

	@XmlElement(name = "numero-cartao-truncado")
	private String truncatedCardNumber;

	private TokenData() {}

	public String getCode() {
		return code;
	}

	public TokenStatus getStatus() {
		return status;
	}

	public String getTruncatedCardNumber() {
		return truncatedCardNumber;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("code", code)
				.add("status", status)
				.add("truncatedCardNumber", truncatedCardNumber)
				.toString();
	}

}
