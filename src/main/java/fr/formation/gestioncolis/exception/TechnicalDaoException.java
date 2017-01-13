package fr.formation.gestioncolis.exception;

public class TechnicalDaoException extends DaoException {

	private static final long serialVersionUID = 1L;

	public TechnicalDaoException() {
		super();
	}

	public TechnicalDaoException(final String message) {
		super(message);
	}

	public TechnicalDaoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public TechnicalDaoException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TechnicalDaoException(final Throwable cause) {
		super(cause);
	}

}
