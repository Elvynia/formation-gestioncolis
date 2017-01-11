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

public abstract class AbstractDao<ENTITY> {

	@PersistenceContext
	protected EntityManager em;

	@Resource
	private UserTransaction transaction;

	public void create(final ENTITY product) {
		this.executeWithTransaction(() -> this.em.persist(product));
		// this.executeWithTransaction(new Runnable() {
		// @Override
		// public void run() {
		// AbstractDao.this.em.persist(product);
		// }
		// });
	}

	public void delete(final ENTITY product) {
		this.executeWithTransaction(() -> this.em.remove(product));
	}

	protected void executeWithTransaction(final Runnable runnable) {
		try {
			this.transaction.begin();
			try {
				runnable.run();
				this.transaction.commit();
			} catch (final PersistenceException e) {
				this.transaction.rollback();
				e.printStackTrace();
			}
		} catch (NotSupportedException | SystemException | SecurityException
				| IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
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

	public void update(final ENTITY product) {
		this.executeWithTransaction(() -> this.em.merge(product));
	}
}
