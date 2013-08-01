package br.com.oncast.acquirerpimp.bean.payment.token;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(Integer.class)
public enum TokenStatus {

	@XmlEnumValue("0")
	BLOCKED,

	@XmlEnumValue("1")
	UNBLOCKED;

}
