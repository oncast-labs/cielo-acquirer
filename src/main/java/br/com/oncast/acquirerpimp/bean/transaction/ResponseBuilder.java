package br.com.oncast.acquirerpimp.bean.transaction;

public interface ResponseBuilder<T extends CieloTransactionResponse> {

	T build(Object reponse);

}
