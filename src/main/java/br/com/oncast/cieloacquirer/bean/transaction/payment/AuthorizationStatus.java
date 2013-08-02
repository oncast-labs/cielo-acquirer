package br.com.oncast.cieloacquirer.bean.transaction.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "lr")
@XmlEnum(String.class)
public enum AuthorizationStatus {

	@XmlEnumValue("00")
	AUTHORIZED,

	@XmlEnumValue("01")
	REFERED_BY_THE_ISSUER,

	@XmlEnumValue("04")
	RESTRICTION_1,

	@XmlEnumValue("05")
	NOT_AUTHORIZED,

	@XmlEnumValue("06")
	TRY_AGAIN_1,

	@XmlEnumValue("07")
	RESTRICTION_2,

	@XmlEnumValue("12")
	INVALID_TRANSACTION_1,

	@XmlEnumValue("13")
	INVALID_VALUE,

	@XmlEnumValue("14")
	INVALID_CREDIT_CARD,

	@XmlEnumValue("15")
	INVALID_ISSUER,

	@XmlEnumValue("41")
	RESTRICTION_3,

	@XmlEnumValue("51")
	INSUFFICIENT_FUNDS,

	@XmlEnumValue("54")
	CREDIT_CARD_EXPIRED,

	@XmlEnumValue("57")
	TRANSACTION_NOT_ALLOWED_1,

	@XmlEnumValue("58")
	TRANSACTION_NOT_ALLOWED_2,

	@XmlEnumValue("62")
	RESTRICTION_4,

	@XmlEnumValue("63")
	RESTRICTION_5,

	@XmlEnumValue("76")
	TRY_AGAIN_2,

	@XmlEnumValue("78")
	CARD_NEEDS_UNBLOCKING,

	@XmlEnumValue("82")
	INVALID_TRANSACTION_2,

	@XmlEnumValue("91")
	BANK_UNAVAILABLE,

	@XmlEnumValue("96")
	TRY_AGAIN_3,

	@XmlEnumValue("AA")
	TRY_AGAIN_4,

	@XmlEnumValue("AC")
	DEBT_CARD_TRYING_TO_USE_CREDIT_PAYMENT_TYPE,

	@XmlEnumValue("GA")
	REFEREC_BY_CIELO,

	@XmlEnumValue("N7")
	INVALID_SECURITY_CODE,

}
