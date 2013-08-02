package br.com.oncast.cieloacquirer.bean.payment.token;

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

	TokenData() {}

	public TokenData(final String tokenCode) {
		code = tokenCode;
	}

	public TokenData(final String tokenCode, final TokenStatus status, final String truncatedCardNumber) {
		code = tokenCode;
		this.status = status;
		this.truncatedCardNumber = truncatedCardNumber;
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final TokenData other = (TokenData) obj;
		if (code == null) {
			if (other.code != null) return false;
		} else if (!code.equals(other.code)) return false;
		return true;
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
