package br.com.oncast.acquirerpimp.bean.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "moeda")
@XmlEnum(Integer.class)
public enum OrderCurrency {

	@XmlEnumValue("986")
	BRL,

	@XmlEnumValue("840")
	USD;

}
