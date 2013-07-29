package br.com.oncast.acquirerpimp.bean.creditcard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "bandeira")
@XmlEnum(String.class)
public enum CreditCardFlag {

	@XmlEnumValue("visa")
	VISA("^4[0-9]{12}(?:[0-9]{3})?$"),

	@XmlEnumValue("mastercard")
	MASTER_CARD("^5[1-5][0-9]{14}$"),

	@XmlEnumValue("amex")
	AMERICAN_EXPRESS("^3[47][0-9]{13}$"),

	@XmlEnumValue("diners")
	DINERS("^3(?:0[0-5]|[68][0-9])[0-9]{11}$"),

	@XmlEnumValue("discover")
	DISCOVER("^6(?:011|5[0-9]{2})[0-9]{12}$"),

	@XmlEnumValue("jcb")
	JCB("^(?:2131|1800|35\\d{3})\\d{11}$"),

	@XmlEnumValue("elo")
	ELO(null),

	@XmlEnumValue("aura")
	AURA(null);

	private static final String GENERIC_CREDIT_CARD_PATTERN = "[0-9]{13,16}";

	private String cardRegexPattern;

	private CreditCardFlag(final String regexPattern) {
		this.cardRegexPattern = regexPattern;
	}

	public static CreditCardFlag getFlag(String cardNumber) {
		for (CreditCardFlag flag : values()) {
			if (flag.acceptsStrictly(cardNumber)) return flag;
		}
		return null;
	}

	public boolean accepts(String cardNumber) {
		return cardNumber.matches(cardRegexPattern == null ? GENERIC_CREDIT_CARD_PATTERN : cardRegexPattern);
	}

	public boolean acceptsStrictly(String cardNumber) {
		return cardRegexPattern != null && cardNumber.matches(cardRegexPattern);
	}

}
