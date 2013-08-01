package br.com.oncast.acquirerpimp.bean.transaction.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import br.com.oncast.acquirerpimp.bean.payment.CieloTimestamp;

import com.google.common.base.Objects;

@XmlType(name = "autorizacao")
@XmlAccessorType(XmlAccessType.NONE)
public class AuthorizationData implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "codigo")
	private PaymentStatus code;

	@XmlElement(name = "mensagem")
	private String message;

	@XmlElement(name = "data-hora")
	private CieloTimestamp timestamp;

	@XmlElement(name = "valor")
	private long value;

	@XmlElement(name = "lr")
	private AuthorizationStatus authorizationStatus;

	@XmlElement(name = "arp")
	private int arp;

	@XmlElement(name = "nsu")
	private int nsu;

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("code", code)
				.add("message", message)
				.add("timestamp", timestamp)
				.add("value", value)
				.add("authorizationStatus", authorizationStatus)
				.add("arp", arp)
				.add("nsu", nsu)
				.toString();
	}

}
