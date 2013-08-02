package br.com.oncast.cieloacquirer.bean.payment.creditcard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "indicador")
@XmlEnum(Integer.class)
public enum SecurityCodeIndicator {

	@XmlEnumValue("0")
	NOT_INFORMED,

	@XmlEnumValue("1")
	INFORMED,

	@XmlEnumValue("2")
	UNREADABLE,

	@XmlEnumValue("9")
	INEXISTENT;

}
