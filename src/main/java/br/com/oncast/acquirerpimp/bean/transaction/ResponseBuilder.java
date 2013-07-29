package br.com.oncast.acquirerpimp.bean.transaction;


public interface ResponseBuilder<T extends CieloResponse> {

	T fromXmlString(String xml);

}
