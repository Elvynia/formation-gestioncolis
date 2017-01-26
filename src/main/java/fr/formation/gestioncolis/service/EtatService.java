package fr.formation.gestioncolis.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.dao.EtatDao;
import fr.formation.gestioncolis.entity.Etat;
import fr.formation.gestioncolis.exception.FunctionalDaoException;

@ManagedBean
@ApplicationScoped
public class EtatService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EtatService.class);

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	public boolean delete(final int etatId) {
		try {
			final Etat etat = this.etatDao.read(etatId);
			if (etat != null) {
				int order = etat.getOrdre();
				final int maxOrder = this.getMaxOrder();
				while (order <= maxOrder) {
					final Etat next = this.getNext(order);
					next.setOrdre(order++);
					this.etatDao.update(next);
				}
				this.etatDao.delete(etatId);
			} else {
				return false;
			}
		} catch (final FunctionalDaoException e) {
			EtatService.LOGGER.error("Erreur pendant la suppression d'un etat",
					e);
			return false;
		}
		return true;
	}

	public Etat getFirst() {
		return this.etatDao.readByOrder(0);
	}

	public int getMaxOrder() {
		int max = 0;
		for (final Etat etat : this.etatDao.readAll()) {
			if (etat.getOrdre() > max) {
				max = etat.getOrdre();
			}
		}
		return max;
	}

	public Etat getNext(final Integer currentOrder) {
		return this.etatDao.readByOrder(currentOrder + 1);
	}

	public boolean save(final Etat etat) {
		try {
			if (etat.getId() < 0) {
				this.etatDao.create(etat);
			} else {
				this.etatDao.update(etat);
			}
		} catch (final FunctionalDaoException e) {
			EtatService.LOGGER.error("Erreur pendant la sauvegarde de l'état.",
					e);
			return false;
		}
		return true;
	}

	/**
	 * @param etatDao the etatDao to set
	 */
	public void setEtatDao(final EtatDao etatDao) {
		this.etatDao = etatDao;
	}
}
