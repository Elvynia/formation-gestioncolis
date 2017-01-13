package fr.formation.gestioncolis.exception;

public class CreateEntityException extends FunctionalDaoException {

	private static final long serialVersionUID = 1L;

	public CreateEntityException() {
		super();
	}

	public CreateEntityException(final String message) {
		super(message);
	}

	public CreateEntityException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public CreateEntityException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CreateEntityException(final Throwable cause) {
		super(cause);
	}

}
