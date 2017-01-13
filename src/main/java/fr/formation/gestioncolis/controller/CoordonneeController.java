package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.CoordonneeBean;
import fr.formation.gestioncolis.dao.CoordonneeDao;
import fr.formation.gestioncolis.entity.Coordonnee;
import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.DeleteEntityException;
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

	/**
	 * @return the coordonnees
	 */
	public List<Coordonnee> getCoordonnees() {
		return this.coordonnees;
	}

	public String save() {
		final Coordonnee coord = new Coordonnee();
		coord.setAddressLine1(this.coordonneeBean.getAddressLine1());
		coord.setAddressLine2(this.coordonneeBean.getAddressLine1());
		coord.setCity(this.coordonneeBean.getCity());
		coord.setCountry(this.coordonneeBean.getCountry());
		coord.setFirstname(this.coordonneeBean.getFirstname());
		coord.setLastname(this.coordonneeBean.getLastname());
		coord.setPostalCode(this.coordonneeBean.getPostalCode());
		coord.setAddressLine1(this.coordonneeBean.getAddressLine1());

		try {
			this.coordonneeDao.create(coord);
			FacesMessages.info("La coordonnée a été créée avec succès.");
		} catch (final CreateEntityException e) {
			CoordonneeController.LOGGER.error(
					"Erreur pendant la création d'une nouvelle coordonnée.", e);
			FacesMessages.error("Impossible de créer la coordonnée.");
		}

		return "/views/dashboard";
	}

	/**
	 * @param coordonneeBean the coordonneeBean to set
	 */
	public void setCoordonneeBean(final CoordonneeBean coordonneeBean) {
		this.coordonneeBean = coordonneeBean;
	}

	/**
	 * @param coordonneeDao the coordonneeDao to set
	 */
	public void setCoordonneeDao(final CoordonneeDao coordonneeDao) {
		this.coordonneeDao = coordonneeDao;
	}

	/**
	 * @param coordonneetBean the coordonneetBean to set
	 */
	public void setCoordonneetBean(final CoordonneeBean coordonneetBean) {
		this.coordonneeBean = coordonneetBean;
	}

}
