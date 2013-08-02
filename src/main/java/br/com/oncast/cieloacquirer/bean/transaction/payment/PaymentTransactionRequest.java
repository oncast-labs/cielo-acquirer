package br.com.oncast.cieloacquirer.bean.transaction.payment;

import java.net.URL;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.cieloacquirer.bean.establishment.Establishment;
import br.com.oncast.cieloacquirer.bean.payment.OrderData;
import br.com.oncast.cieloacquirer.bean.payment.PaymentData;
import br.com.oncast.cieloacquirer.bean.payment.PaymentSource;
import br.com.oncast.cieloacquirer.bean.transaction.CieloTransactionRequest;

@XmlRootElement(name = "requisicao-transacao")
@XmlAccessorType(XmlAccessType.NONE)
public class PaymentTransactionRequest implements CieloTransactionRequest<PaymentTransactionResponse> {

	private static final long serialVersionUID = 1L;

	@XmlAttribute(name = "id")
	private String id;

	@XmlAttribute(name = "versao")
	private String version;

	@XmlElement(name = "dados-ec")
	private Establishment establishment;

	@XmlAnyElement
	private PaymentSource carrierData;

	@XmlElement(name = "dados-pedido")
	private OrderData orderData;

	@XmlElement(name = "forma-pagamento")
	private PaymentData paymentType;

	@XmlElement(name = "url-retorno", required = false)
	private String returnUrl;

	@XmlElement(name = "autorizar")
	private AuthorizationType authorizationType;

	@XmlElement(name = "capturar")
	private boolean autoCaptureWhenAuthorized;

	PaymentTransactionRequest() {}

	public PaymentTransactionRequest(final PaymentSource creditCard, final OrderData orderData, final PaymentData paymentType, final AuthorizationType authorizationType) {
		this.carrierData = creditCard;
		this.orderData = orderData;
		this.paymentType = paymentType;
		this.authorizationType = authorizationType;
		this.autoCaptureWhenAuthorized = false;
	}

	public PaymentTransactionRequest setReturnUrl(final URL returnUrl) {
		this.returnUrl = returnUrl.toString();
		return this;
	}

	public PaymentTransactionRequest setAutoCaptureWhenAuthorized(final boolean capture) {
		this.autoCaptureWhenAuthorized = capture;
		return this;
	}

	public Class<PaymentTransactionResponse> getResponseType() {
		return PaymentTransactionResponse.class;
	}

	public CieloTransactionRequest<PaymentTransactionResponse> setEstablishment(final Establishment establishment) {
		this.establishment = establishment;
		return this;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

}
