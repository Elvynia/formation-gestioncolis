package fr.formation.gestioncolis.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.EtatBean;
import fr.formation.gestioncolis.bean.EtatOrderBean;
import fr.formation.gestioncolis.entity.Etat;
import fr.formation.gestioncolis.service.EtatService;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class EtatController implements Serializable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EtatController.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{etatBean}")
	private EtatBean etatBean;

	private int etatId;

	@ManagedProperty("#{etatOrderBean}")
	private EtatOrderBean etatOrderBean;

	@ManagedProperty("#{etatService}")
	private EtatService etatService;

	@PostConstruct
	public void _init() {
		this.etatId = -1;
	}

	public void delete() {
		if (this.etatService.delete(this.etatId)) {
			FacesMessages.info(
					"Etat d'id " + this.etatId + " supprimé avec succès.");
			this.etatId = -1;
			this.etatOrderBean.refresh();
		} else {
			FacesMessages.error(
					"Impossible de supprimer l'état d'id " + this.etatId);
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
		final Etat etat = new Etat(this.etatBean.getNom());
		if (this.etatBean.getId() != null) {
			etat.setId(this.etatBean.getId());
		} else {
			etat.setOrdre(this.etatOrderBean.getList().size());
		}
		if (this.etatService.save(etat)) {
			FacesMessages.info("Nouvel état '" + this.etatBean.getNom()
					+ "' créé avec succès.");
			this.etatBean.setNom(null);
		} else {
			FacesMessages.error("Impossible de créer l'état avec le nom "
					+ this.etatBean.getNom());
		}
		this.etatId = -1;
		this.etatOrderBean.refresh();
	}

	public void saveOrder() {
		EtatController.LOGGER.debug("Sauvegarde de l'ordre des états");
		boolean success = true;
		for (final Etat etat : this.etatOrderBean.getList()) {
			EtatController.LOGGER.debug("Etat : id={}, nom={}, ordre={}",
					etat.getId(), etat.getNom(), etat.getOrdre());
			success = success && this.etatService.save(etat);
		}
		if (success) {
			FacesMessages
					.info("L'ordre des états à été sauvegadé avec succès.");
		} else {
			FacesMessages.error(
					"Un problème est survenu pendant la sauvegarde de l'ordre des états.");
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

	/**
	 * @param etatService the etatService to set
	 */
	public void setEtatService(final EtatService etatService) {
		this.etatService = etatService;
	}

	public void unselectEtat(final UnselectEvent event) {
		this.etatId = -1;
	}

}
