package br.com.oncast.cieloacquirer.bean.payment;

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
