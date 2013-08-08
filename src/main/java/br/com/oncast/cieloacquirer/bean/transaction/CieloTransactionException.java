package br.com.oncast.cieloacquirer.bean.transaction;

import br.com.oncast.cieloacquirer.bean.transaction.error.CieloTransactionError;

public class CieloTransactionException extends Exception {

	private static final long serialVersionUID = 1L;

	private CieloTransactionError transactionError;

	CieloTransactionException() {}

	public CieloTransactionException(final CieloTransactionError transactionError) {
		super(transactionError.getMessage());
		this.transactionError = transactionError;
	}

	public CieloTransactionError getTransactionError() {
		return transactionError;
	}

	@Override
	public String toString() {
		return "CieloTransactionError: Cielo WS returned with status (" + transactionError.getType() + "): " + getMessage();
	}
}
