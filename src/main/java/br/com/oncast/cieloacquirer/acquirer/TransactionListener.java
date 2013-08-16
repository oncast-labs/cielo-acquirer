package br.com.oncast.cieloacquirer.acquirer;

public interface TransactionListener {

	void onTransactionSent(String sentRequestXml, String receivedResponseXml);

}
