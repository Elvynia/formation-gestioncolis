package fr.formation.gestioncolis.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.DaoException;
import fr.formation.gestioncolis.exception.DeleteEntityException;
import fr.formation.gestioncolis.exception.FunctionalDaoException;
import fr.formation.gestioncolis.exception.TechnicalDaoException;
import fr.formation.gestioncolis.exception.UpdateEntityException;

public abstract class AbstractDao<ENTITY> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractDao.class);

	@PersistenceContext
	protected EntityManager em;

	@Resource
	private UserTransaction transaction;

	public void create(final ENTITY product) throws CreateEntityException {
		try {
			this.executeWithTransaction(() -> this.em.persist(product));
		} catch (final FunctionalDaoException e) {
			throw new CreateEntityException(
					"Impossible de créer l'entité à cause "
							+ "d'une containte de la base de données.",
					e);
		} catch (final DaoException e) {
			AbstractDao.LOGGER.error(
					"Erreur technique pendant la création de l'entité.", e);
		}
		// this.executeWithTransaction(new Runnable() {
		// @Override
		// public void run() {
		// AbstractDao.this.em.persist(product);
		// }
		// });
	}

	public void delete(final Integer id) throws DeleteEntityException {
		try {
			this.executeWithTransaction(() -> this.em.remove(this.read(id)));
		} catch (final FunctionalDaoException e) {
			throw new DeleteEntityException(
					"Impossible de supprimer l'entité à cause "
							+ "d'une contrainte de la base de données.",
					e);
		} catch (final DaoException e) {
			AbstractDao.LOGGER.error(
					"Erreur technique pendant la suppression de l'entité.", e);
		}
	}

	protected void executeWithTransaction(final Runnable runnable)
			throws DaoException {
		try {
			this.transaction.begin();
			try {
				runnable.run();
				this.transaction.commit();
			} catch (final PersistenceException e) {
				this.transaction.rollback();
				AbstractDao.LOGGER.error(
						"Erreur pendant l'exécution d'une transaction.", e);
				if (ExceptionUtils.indexOfThrowable(e,
						DataException.class) >= 0) {
					throw new FunctionalDaoException(e);
				}
			}
		} catch (NotSupportedException | SystemException | SecurityException
				| IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			AbstractDao.LOGGER.error("Erreur de gestion de la transaction.", e);
			if (ExceptionUtils.indexOfThrowable(e,
					ConstraintViolationException.class) >= 0) {
				throw new FunctionalDaoException(e);
			} else {
				throw new TechnicalDaoException(
						"Erreur pendant l'exécution des traitements du DAO.",
						e);
			}
		}
	}

	protected ENTITY read(final Class<ENTITY> clazz, final Integer id) {
		return this.em.find(clazz, id);
	}

	public abstract ENTITY read(final Integer id);

	public abstract List<ENTITY> readAll();

	protected List<ENTITY> readAll(final Class<ENTITY> clazz) {
		final TypedQuery<ENTITY> query = this.em.createNamedQuery(
				clazz.getSimpleName().concat(".findAll"), clazz);
		return query.getResultList();
	}

	public void update(final ENTITY product) throws UpdateEntityException {
		try {
			this.executeWithTransaction(() -> this.em.merge(product));
		} catch (final FunctionalDaoException e) {
			throw new UpdateEntityException(
					"Impossible de mettre à jour l'entité à cause "
							+ "d'une contrainte de la base de données.",
					e);
		} catch (final DaoException e) {
			AbstractDao.LOGGER.error(
					"Erreur technique pendant la mise à jour de l'entité.", e);
		}
	}
}
