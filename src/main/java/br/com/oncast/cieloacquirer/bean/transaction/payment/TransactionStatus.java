package br.com.oncast.cieloacquirer.bean.transaction.payment;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum(Integer.class)
public enum TransactionStatus {

	@XmlEnumValue("0")
	CREATED,

	@XmlEnumValue("1")
	IN_PROGRESS,

	@XmlEnumValue("2")
	AUTHENTICATED,

	@XmlEnumValue("3")
	NOT_AUTHENTICATED,

	@XmlEnumValue("4")
	AUTHORIZED {
		@Override
		public boolean sucess() {
			return true;
		}
	},

	@XmlEnumValue("5")
	NOT_AUTHORIZED,

	@XmlEnumValue("6")
	CAPTURED {
		@Override
		public boolean sucess() {
			return true;
		}
	},

	@XmlEnumValue("9")
	CANCELLED,

	@XmlEnumValue("10")
	UNDER_AUTHENTICATION,

	@XmlEnumValue("12")
	UNDER_CANCELLATION;

	public boolean sucess() {
		return false;
	}

}
