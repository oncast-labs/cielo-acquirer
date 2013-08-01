package br.com.oncast.acquirerpimp.bean.transaction.error;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "erro")
@XmlAccessorType(XmlAccessType.NONE)
public class CieloTransactionError implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "codigo")
	private CieloErrorType type;

	@XmlElement(name = "mensagem")
	private String message;

	public CieloErrorType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setType(CieloErrorType type) {
		this.type = type;
	}

}
