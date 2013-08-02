package br.com.oncast.cieloacquirer.bean.transaction.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "eci")
@XmlEnum(Integer.class)
public enum AuthenticationStatus {

	@XmlEnumValue("0")
	NOT_AUTHENTICATED_1,

	@XmlEnumValue("1")
	NOT_ABLE_TO_AUTHENTICATE_MASTER_CARD,

	@XmlEnumValue("2")
	AUTHENTICATED_MASTER_CARD,

	@XmlEnumValue("5")
	AUTHENTICATED_VISA,

	@XmlEnumValue("6")
	NOT_ABLE_TO_AUTHENTICATE_VISA,

	@XmlEnumValue("7")
	NOT_AUTHENTICATED_2;

}
