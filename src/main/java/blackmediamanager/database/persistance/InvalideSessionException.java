package blackmediamanager.database.persistance;

public class InvalideSessionException extends Exception {
	private static final long serialVersionUID = 1344603032796871359L;

	public static InvalideSessionException SessionNull() {
		return new InvalideSessionException("Session Null");
	}

	public static InvalideSessionException SessionNotOpen() {
		return new InvalideSessionException("Session Closed");
	}

	public static InvalideSessionException SessionNotConnected() {
		return new InvalideSessionException("Session not Connected");
	}

	private InvalideSessionException(String reason) {
		super("The used Session is invalide, reason: " + reason);
	}
}
