package fr.formation.gestioncolis.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.EtatBean;
import fr.formation.gestioncolis.bean.EtatOrderBean;
import fr.formation.gestioncolis.dao.EtatDao;
import fr.formation.gestioncolis.entity.Etat;
import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.UpdateEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class EtatController implements Serializable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EtatController.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{etatBean}")
	private EtatBean etatBean;

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	@ManagedProperty("#{etatOrderBean}")
	private EtatOrderBean etatOrderBean;

	public void save() {
		EtatController.LOGGER.debug("Sauvegarde de etatBean en BDD.");
		if (this.etatBean.getId() == null) {
			try {
				final Etat etat = new Etat(this.etatBean.getNom());
				etat.setOrdre(this.etatOrderBean.getList().size());
				this.etatDao.create(etat);
				FacesMessages.info("Nouvel état '" + this.etatBean.getNom()
						+ "' créé avec succès.");
				this.etatBean.setNom(null);
			} catch (final CreateEntityException e) {
				EtatController.LOGGER.error(
						"Erreur pendant la création d'un nouvel etat", e);
				FacesMessages.error("Impossible de créer l'état avec le nom "
						+ this.etatBean.getNom());
			}
		} else {
			// TODO: Modification.
		}
		this.etatOrderBean.refresh();
	}

	public void saveOrder() {
		EtatController.LOGGER.debug("Sauvegarde de l'ordre des états");
		try {
			for (final Etat etat : this.etatOrderBean.getList()) {
				EtatController.LOGGER.debug("Etat : id={}, nom={}, ordre={}",
						etat.getId(), etat.getNom(), etat.getOrdre());
				this.etatDao.update(etat);
			}
			FacesMessages
					.info("L'ordre des états à été sauvegadé avec succès.");
		} catch (final UpdateEntityException e) {
			EtatController.LOGGER.error(
					"Erreur pendant la mise à jour de l'ordre des états.", e);
			FacesMessages
					.error("Impossible de mettre à jour l'ordre des états.");
		}
	}

	/**
	 * @param etatBean the etatBean to set
	 */
	public void setEtatBean(final EtatBean etatBean) {
		this.etatBean = etatBean;
	}

	/**
	 * @param etatDao the etatDao to set
	 */
	public void setEtatDao(final EtatDao etatDao) {
		this.etatDao = etatDao;
	}

	/**
	 * @param etatOrderBean the etatOrderBean to set
	 */
	public void setEtatOrderBean(final EtatOrderBean etatOrderBean) {
		this.etatOrderBean = etatOrderBean;
	}

}
