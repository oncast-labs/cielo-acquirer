package br.com.oncast.cieloacquirer.bean.transaction;

import br.com.oncast.cieloacquirer.bean.transaction.error.CieloTransactionError;

public class CieloTransactionException extends Exception {

	private CieloTransactionError transactionError;

	CieloTransactionException() {}

	public CieloTransactionException(final CieloTransactionError transactionError) {
		super("Cielo WS returned with status (" + transactionError.getType() + "): " + transactionError.getMessage());
		this.transactionError = transactionError;
	}

	public CieloTransactionError getTransactionError() {
		return transactionError;
	}

	private static final long serialVersionUID = 1L;

}
