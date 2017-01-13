package fr.formation.gestioncolis.exception;

public class DeleteEntityException extends FunctionalDaoException {

	private static final long serialVersionUID = 1L;

	public DeleteEntityException() {
		super();
	}

	public DeleteEntityException(final String message) {
		super(message);
	}

	public DeleteEntityException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DeleteEntityException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeleteEntityException(final Throwable cause) {
		super(cause);
	}

}
