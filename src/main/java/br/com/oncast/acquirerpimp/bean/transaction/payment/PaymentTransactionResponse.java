package br.com.oncast.acquirerpimp.bean.transaction.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.acquirerpimp.bean.payment.OrderData;
import br.com.oncast.acquirerpimp.bean.payment.PaymentData;
import br.com.oncast.acquirerpimp.bean.transaction.CieloTransactionResponse;

import com.google.common.base.Objects;

@XmlRootElement(name = "transacao")
@XmlAccessorType(XmlAccessType.NONE)
public class PaymentTransactionResponse implements CieloTransactionResponse {

	private static final long serialVersionUID = 1L;

	@XmlAttribute(name = "id")
	private String id;

	@XmlAttribute(name = "versao")
	private String version;

	@XmlElement(name = "tid")
	private String tid;

	@XmlElement(name = "pan")
	private String pan;

	@XmlElement(name = "dados-pedido")
	private OrderData orderData;

	@XmlElement(name = "forma-pagamento")
	private PaymentData paymentData;

	@XmlElement(name = "status")
	private PaymentStatus status;

	@XmlElement(name = "autenticacao")
	private AuthenticationData authenticationData;

	@XmlElement(name = "autorizacao")
	private AuthorizationData authorizationData;

	public PaymentTransactionResponse() {}

	public String getId() {
		return id;
	}

	public String getVersion() {
		return version;
	}

	public String getTid() {
		return tid;
	}

	public String getPan() {
		return pan;
	}

	public OrderData getOrderData() {
		return orderData;
	}

	public PaymentData getPaymentData() {
		return paymentData;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public AuthenticationData getAuthenticationData() {
		return authenticationData;
	}

	public AuthorizationData getAuthorizationData() {
		return authorizationData;
	}

	public void setTid(final String tid) {
		this.tid = tid;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("tid", tid)
				.add("pan", pan)
				.add("orderData", orderData)
				.add("paymentData", paymentData)
				.add("status", status)
				.add("authenticationData", authenticationData)
				.add("authorizationData", authorizationData)
				.toString();
	}

}