package br.com.oncast.acquirerpimp.bean.transaction;

import java.io.Serializable;

import br.com.oncast.acquirerpimp.bean.establishment.Establishment;

public interface CieloTransaction<T extends CieloResponse> extends Serializable {

	ResponseBuilder<T> getResponseBuilder();

	CieloTransaction<T> setEstablishment(Establishment establishment);

}
