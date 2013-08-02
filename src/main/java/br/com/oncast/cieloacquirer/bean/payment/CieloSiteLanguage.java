package br.com.oncast.cieloacquirer.bean.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(String.class)
public enum CieloSiteLanguage {

	@XmlEnumValue("PT")
	PORTUGUESE_BRAZIL,

	@XmlEnumValue("EN")
	ENGLISH,

	@XmlEnumValue("ES")
	SPANISH

}
