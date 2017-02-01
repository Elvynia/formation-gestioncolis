package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.CoordonneeBean;
import fr.formation.gestioncolis.dao.CoordonneeDao;
import fr.formation.gestioncolis.entity.Coordonnee;
import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.DeleteEntityException;
import fr.formation.gestioncolis.exception.UpdateEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class CoordonneeController implements Serializable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CoordonneeController.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{coordonneeBean}")
	private CoordonneeBean coordonneeBean;

	@ManagedProperty("#{coordonneeDao}")
	private CoordonneeDao coordonneeDao;

	private List<Coordonnee> coordonnees;

	private Integer coordId; // pour l'édition

	@PostConstruct
	public void _init() {
		CoordonneeController.LOGGER
				.debug("Chargement de la liste des coordonnées.");
		this.coordonnees = this.coordonneeDao.readAll();
	}

	public String delete(final Coordonnee coord) {
		try {
			this.coordonneeDao.delete(coord.getId());
			this.coordonnees.remove(coord);
			FacesMessages.info("La coordonnée a été supprimée avec succès.");
		} catch (final DeleteEntityException e) {
			CoordonneeController.LOGGER
					.error("Erreur lors de la suppression de la coordonnée d'id="
							+ coord.getId(), e);
			FacesMessages.error(
					"Impossible de supprimer la coordonnée car elle est utilisée dans un paquet.");
		}
		return "/views/coordonnee/display";
	}

	public Integer getCoordId() {
		return this.coordId;
	}

	/**
	 * @return the coordonnees
	 */
	public List<Coordonnee> getCoordonnees() {
		return this.coordonnees;
	}

	public void prepareEdit() {
		final String paramId = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("coordId");
		if (paramId != null) {
			this.coordId = Integer.parseInt(paramId);
			final Coordonnee editCoord = this.coordonneeDao.read(this.getCoordId());
			this.coordonneeBean.setFirstname(editCoord.getFirstname());
			this.coordonneeBean.setLastname(editCoord.getLastname());
			this.coordonneeBean.setAddressLine1(editCoord.getAddressLine1());
			this.coordonneeBean.setAddressLine2(editCoord.getAddressLine2());
			this.coordonneeBean.setPostalCode(editCoord.getPostalCode());
			this.coordonneeBean.setCity(editCoord.getCity());
			this.coordonneeBean.setCountry(editCoord.getCountry());
		}
	}

	public String save() {
		final Coordonnee coord = new Coordonnee();
		coord.setAddressLine1(this.coordonneeBean.getAddressLine1());
		coord.setAddressLine2(this.coordonneeBean.getAddressLine2());
		coord.setCity(this.coordonneeBean.getCity());
		coord.setCountry(this.coordonneeBean.getCountry());
		coord.setFirstname(this.coordonneeBean.getFirstname());
		coord.setLastname(this.coordonneeBean.getLastname());
		coord.setPostalCode(this.coordonneeBean.getPostalCode());
		coord.setAddressLine1(this.coordonneeBean.getAddressLine1());

		try {
			if (this.coordId == null) {
				CoordonneeController.LOGGER
						.debug("Création d'une nouvelle coordonée {}", coord);
				this.coordonneeDao.create(coord);
				FacesMessages.info("La coordonnée a été créée avec succès.");
			} else {
				coord.setId(this.getCoordId());
				CoordonneeController.LOGGER.debug("Mise à jour de la coordonée {}",
						coord);
				this.coordonneeDao.update(coord);
				FacesMessages.info("La coordonée a été mise à jour avec suucès.");
			}
		} catch (final CreateEntityException e) {
			CoordonneeController.LOGGER
					.error("Erreur pendant la création d'une nouvelle coordonnée.", e);
			FacesMessages.error("Impossible de créer la coordonnée.");
		} catch (final UpdateEntityException e) {
			CoordonneeController.LOGGER
					.error("Erreur pendant la mise à jour de la coordonnée {}.", coord);
			FacesMessages.error("Impossible de mettre à jour la coordonnée.");
		}
		return "/views/dashboard";
	}

	public void setCoordId(final Integer coordId) {
		this.coordId = coordId;
	}

	/**
	 * @param coordonneeBean
	 *          the coordonneeBean to set
	 */
	public void setCoordonneeBean(final CoordonneeBean coordonneeBean) {
		this.coordonneeBean = coordonneeBean;
	}

	/**
	 * @param coordonneeDao
	 *          the coordonneeDao to set
	 */
	public void setCoordonneeDao(final CoordonneeDao coordonneeDao) {
		this.coordonneeDao = coordonneeDao;
	}

	/**
	 * @param coordonneetBean
	 *          the coordonneetBean to set
	 */
	public void setCoordonneetBean(final CoordonneeBean coordonneetBean) {
		this.coordonneeBean = coordonneetBean;
	}

}
