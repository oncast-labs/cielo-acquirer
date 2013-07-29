package br.com.oncast.acquirerpimp.bean.transaction;

import java.net.URI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.acquirerpimp.bean.creditcard.CreditCard;
import br.com.oncast.acquirerpimp.bean.establishment.Establishment;
import br.com.oncast.acquirerpimp.bean.payment.OrderData;
import br.com.oncast.acquirerpimp.bean.payment.PaymentData;

@XmlRootElement(name = "requisicao-transacao")
@XmlAccessorType(XmlAccessType.NONE)
public class CieloPaymentTransaction implements CieloTransaction<CieloPaymentTransactionResponse> {

	private static final long serialVersionUID = 1L;

	private static long counter = 0;

	@XmlAttribute(name = "id")
	private String id;

	@XmlAttribute(name = "versao")
	private String version;

	@XmlElement(name = "dados-ec")
	private Establishment establishment;

	@XmlElement(name = "dados-portador")
	private CreditCard creditCard;

	@XmlElement(name = "dados-pedido")
	private OrderData orderData;

	@XmlElement(name = "forma-pagamento")
	private PaymentData paymentType;

	@XmlElement(name = "url-retorno", required = false)
	private String returnUrl;

	@XmlElement(name = "autorizar")
	private TransactionAuthorizationType authorizationType;

	@XmlElement(name = "capturar")
	private boolean autoCaptureWhenAuthorized;

	CieloPaymentTransaction() {}

	public CieloPaymentTransaction(CreditCard creditCard, OrderData orderData, PaymentData paymentType, TransactionAuthorizationType authorizationType) {
		this.id = String.valueOf(++counter);
		this.version = "1.2.1";
		this.creditCard = creditCard;
		this.orderData = orderData;
		this.paymentType = paymentType;
		this.authorizationType = authorizationType;
		this.autoCaptureWhenAuthorized = false;
	}

	public CieloPaymentTransaction setReturnUrl(URI returnUrl) {
		this.returnUrl = returnUrl.toString();
		return this;
	}

	public CieloPaymentTransaction setAutoCaptureWhenAuthorized(boolean capture) {
		this.autoCaptureWhenAuthorized = capture;
		return this;
	}

	public ResponseBuilder<CieloPaymentTransactionResponse> getResponseBuilder() {
		return new ResponseBuilder<CieloPaymentTransactionResponse>() {
			public CieloPaymentTransactionResponse fromXmlString(String xml) {
				return new CieloPaymentTransactionResponse();
			}
		};
	}

	public CieloTransaction<CieloPaymentTransactionResponse> setEstablishment(Establishment establishment) {
		this.establishment = establishment;
		return this;
	}

}
