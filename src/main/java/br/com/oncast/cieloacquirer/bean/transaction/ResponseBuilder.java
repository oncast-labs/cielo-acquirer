package br.com.oncast.cieloacquirer.bean.transaction;

public interface ResponseBuilder<T extends CieloTransactionResponse> {

	T build(Object reponse);

}
