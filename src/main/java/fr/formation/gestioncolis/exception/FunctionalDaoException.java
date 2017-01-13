package fr.formation.gestioncolis.exception;

public class FunctionalDaoException extends DaoException {

	private static final long serialVersionUID = 1L;

	public FunctionalDaoException() {
		super();
	}

	public FunctionalDaoException(final String message) {
		super(message);
	}

	public FunctionalDaoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public FunctionalDaoException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FunctionalDaoException(final Throwable cause) {
		super(cause);
	}

}
