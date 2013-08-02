package br.com.oncast.cieloacquirer.bean.transaction;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.oncast.cieloacquirer.bean.establishment.Establishment;

@XmlRootElement
public interface CieloTransactionRequest<T extends CieloTransactionResponse> extends Serializable {

	String getId();

	void setId(final String id);

	void setVersion(final String version);

	Class<T> getResponseType();

	CieloTransactionRequest<T> setEstablishment(Establishment establishment);

}
