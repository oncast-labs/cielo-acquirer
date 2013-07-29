package br.com.oncast.acquirerpimp.bean.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dados-pedido")
public class OrderData implements Serializable {

	private static final long serialVersionUID = 1L;

	private static long counter = 0;

	@XmlElement(name = "numero")
	private String orderId;

	@XmlElement(name = "valor")
	private long amount;

	@XmlElement(name = "moeda")
	private OrderCurrency currency;

	@XmlElement(name = "data-hora")
	private CieloTimestamp timestamp;

	@XmlElement(name = "descricao", required = false)
	private String description;

	@XmlElement(name = "idioma", required = false)
	private CieloSiteLanguage language;

	@XmlElement(name = "soft-descriptor", required = false)
	private String billDescription;

	OrderData() {}

	public OrderData(long amount) {
		this.orderId = "" + ++counter;
		this.amount = amount;
		this.currency = OrderCurrency.BRL;
		this.timestamp = new CieloTimestamp();
	}

	public OrderData setCurrency(OrderCurrency currency) {
		this.currency = currency;
		return this;
	}

	public OrderData setDescription(String description) {
		this.description = description;
		return this;
	}

	public OrderData setCieloSiteLanguage(CieloSiteLanguage language) {
		this.language = language;
		return this;
	}

	public OrderData setBillDescription(String billDescription) {
		this.billDescription = billDescription;
		return this;
	}

}
