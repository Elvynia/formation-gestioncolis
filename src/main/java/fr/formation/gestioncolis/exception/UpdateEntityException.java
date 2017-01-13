package fr.formation.gestioncolis.exception;

public class UpdateEntityException extends FunctionalDaoException {

	private static final long serialVersionUID = 1L;

	public UpdateEntityException() {
		super();
	}

	public UpdateEntityException(final String message) {
		super(message);
	}

	public UpdateEntityException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public UpdateEntityException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UpdateEntityException(final Throwable cause) {
		super(cause);
	}

}
