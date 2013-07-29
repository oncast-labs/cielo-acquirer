package br.com.oncast.acquirerpimp.bean.token;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "status")
@XmlEnum(Integer.class)
public enum CieloTokenStatus {

	@XmlEnumValue("0")
	BLOCKED,

	@XmlEnumValue("1")
	UNBLOCKED;

}
