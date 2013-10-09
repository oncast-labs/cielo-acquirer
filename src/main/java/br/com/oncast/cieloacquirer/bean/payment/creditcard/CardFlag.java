package br.com.oncast.cieloacquirer.bean.payment.creditcard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import com.google.common.base.CaseFormat;

@XmlType(name = "bandeira")
@XmlEnum(String.class)
public enum CardFlag {

	@XmlEnumValue("visa")
	VISA("^4[0-9]{12}(?:[0-9]{3})?$"),

	@XmlEnumValue("mastercard")
	MASTER_CARD("^5[1-5][0-9]{14}$"),

	@XmlEnumValue("amex")
	AMERICAN_EXPRESS("^3[47][0-9]{13}$") {
		@Override
		public int getSecurityCodeLength() {
			return 4;
		}
	},

	@XmlEnumValue("diners")
	DINERS("^3(?:0[0-5]|[68][0-9])[0-9]{11}$"),

	@XmlEnumValue("discover")
	DISCOVER("^6(?:011|5[0-9]{2})[0-9]{12}$"),

	@XmlEnumValue("jcb")
	JCB("^(?:2131|1800|35\\d{3})\\d{11}$"),

	@XmlEnumValue("elo")
	ELO("^63[0-9]{14}$"),

	@XmlEnumValue("aura")
	AURA("^50[0-9]{17}$");

	public static final int GENERIC_SECURTITY_CODE_LENGTH = 3;

	private static final String GENERIC_CARD_PATTERN = "^(?:[0-9]{13,16}|[0-9]{19})$";

	private String cardRegexPattern;

	private CardFlag(final String regexPattern) {
		this.cardRegexPattern = regexPattern;
	}

	public static CardFlag getFlag(final String cardNumber) {
		for (final CardFlag flag : values()) {
			if (flag.acceptsStrictly(cardNumber)) return flag;
		}
		return null;
	}

	public static boolean isValidCard(final CardFlag flag, final String cardNumber) {
		return flag == null ? cardNumber.matches(GENERIC_CARD_PATTERN) : flag.accepts(cardNumber);
	}

	public static boolean isValidSecurityCode(final CardFlag flag, final String securityCode) {
		final int length = flag != null ? flag.getSecurityCodeLength() : CardFlag.GENERIC_SECURTITY_CODE_LENGTH;
		return securityCode.length() == length;
	}

	@Override
	public String toString() {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name());
	}

	public int getSecurityCodeLength() {
		return GENERIC_SECURTITY_CODE_LENGTH;
	}

	public boolean accepts(final String cardNumber) {
		return cardNumber.matches(cardRegexPattern == null ? GENERIC_CARD_PATTERN : cardRegexPattern);
	}

	public boolean acceptsStrictly(final String cardNumber) {
		return cardRegexPattern != null && cardNumber.matches(cardRegexPattern);
	}

}
