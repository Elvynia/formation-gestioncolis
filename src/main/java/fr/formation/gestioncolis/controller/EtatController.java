package fr.formation.gestioncolis.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.EtatBean;
import fr.formation.gestioncolis.bean.EtatOrderBean;
import fr.formation.gestioncolis.dao.EtatDao;
import fr.formation.gestioncolis.entity.Etat;
import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.DeleteEntityException;
import fr.formation.gestioncolis.exception.UpdateEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
// FIXME ViewScoped not read because of package view meant for CDI beans ?
public class EtatController implements Serializable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EtatController.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{etatBean}")
	private EtatBean etatBean;

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	private int etatId;

	@ManagedProperty("#{etatOrderBean}")
	private EtatOrderBean etatOrderBean;

	@PostConstruct
	public void _init() {
		this.etatId = -1;
	}

	public void delete() {
		if (this.etatId >= 0) {
			try {
				this.etatDao.delete(this.etatId);
				FacesMessages.info(
						"Etat d'id " + this.etatId + " supprimé avec succès.");
			} catch (final DeleteEntityException e) {
				EtatController.LOGGER.error(
						"Erreur pendant la suppression d'un nouvel etat", e);
				FacesMessages.error(
						"Impossible de supprimer l'état d'id " + this.etatId);
			}
			this.etatId = -1;
			this.etatOrderBean.refresh();
		} else {
			FacesMessages
					.error("Impossible de supprimer : aucun état sélectionné.");
		}
	}

	/**
	 * @return the etatId
	 */
	public int getEtatId() {
		return this.etatId;
	}

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

	public void selectEtat(final SelectEvent event) {
		this.etatId = Integer.parseInt(event.getObject().toString());
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
	 * @param etatId the etatId to set
	 */
	public void setEtatId(final int etatId) {
		this.etatId = etatId;
	}

	/**
	 * @param etatOrderBean the etatOrderBean to set
	 */
	public void setEtatOrderBean(final EtatOrderBean etatOrderBean) {
		this.etatOrderBean = etatOrderBean;
	}

	public void unselectEtat(final UnselectEvent event) {
		this.etatId = -1;
	}

}
