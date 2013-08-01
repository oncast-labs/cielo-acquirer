package br.com.oncast.acquirerpimp.bean.transaction;

import java.io.Serializable;

import br.com.oncast.acquirerpimp.bean.establishment.Establishment;

public interface CieloTransactionRequest<T extends CieloTransactionResponse> extends Serializable {

	String getId();

	void setId(final String id);

	void setVersion(final String version);

	Class<T> getResponseType();

	CieloTransactionRequest<T> setEstablishment(Establishment establishment);

}
