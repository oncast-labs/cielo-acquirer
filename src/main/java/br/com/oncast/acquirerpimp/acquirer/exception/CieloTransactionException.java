package br.com.oncast.acquirerpimp.acquirer.exception;

import br.com.oncast.acquirerpimp.bean.transaction.error.CieloTransactionError;

public class CieloTransactionException extends Exception {

	private final CieloTransactionError transactionError;

	public CieloTransactionException(final CieloTransactionError transactionError) {
		super("Cielo WS returned with status (" + transactionError.getType() + "): " + transactionError.getMessage());
		this.transactionError = transactionError;
	}

	public CieloTransactionError getTransactionError() {
		return transactionError;
	}

	private static final long serialVersionUID = 1L;

}
