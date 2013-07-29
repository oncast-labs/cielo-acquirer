package br.com.oncast.acquirerpimp.bean.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "produto")
@XmlEnum(String.class)
public enum PaymentType {

	@XmlEnumValue("1")
	CREDIT,

	@XmlEnumValue("2")
	INSTALLMENT_BY_STORE,

	@XmlEnumValue("3")
	INSTALLMENT_BY_CARD_ADMINISTRATOR,

	@XmlEnumValue("A")
	DEBT;

}
