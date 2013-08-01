package br.com.oncast.acquirerpimp.bean.payment;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.common.base.Objects;

@XmlType(name = "dados-pedido")
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
	private String softDescriptor;

	OrderData() {}

	public OrderData(final long amount) {
		this.orderId = "" + ++counter;
		this.amount = amount;
		this.currency = OrderCurrency.BRL;
		this.timestamp = new CieloTimestamp();
	}

	public OrderData setCurrency(final OrderCurrency currency) {
		this.currency = currency;
		return this;
	}

	public OrderData setDescription(final String description) {
		this.description = description;
		return this;
	}

	public OrderData setCieloSiteLanguage(final CieloSiteLanguage language) {
		this.language = language;
		return this;
	}

	public OrderData setBillDescription(final String billDescription) {
		this.softDescriptor = billDescription;
		return this;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("orderId", orderId)
				.add("amount", amount)
				.add("currency", currency)
				.add("timestamp", timestamp)
				.add("description", description)
				.add("language", language)
				.add("softDescriptor", softDescriptor)
				.toString();
	}

}
