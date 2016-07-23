package blackmediamanager.database.persistance;

public class InvalideTransactionException extends Exception {
	private static final long serialVersionUID = 6267529011887032048L;

	public static InvalideTransactionException TransactionNull() {
		return new InvalideTransactionException("Transaction Null");
	}

	public static InvalideTransactionException TransactionNotActive() {
		return new InvalideTransactionException("Transaction Not Active");
	}

	private InvalideTransactionException(String reason) {
		super("The used Transaction is invalide, reason: " + reason);
	}
}
