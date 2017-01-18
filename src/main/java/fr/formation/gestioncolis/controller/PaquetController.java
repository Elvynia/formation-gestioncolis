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
import fr.formation.gestioncolis.bean.PaquetBean;
import fr.formation.gestioncolis.bean.ProductBean;
import fr.formation.gestioncolis.dao.PaquetDao;
import fr.formation.gestioncolis.entity.Paquet;
import fr.formation.gestioncolis.exception.DeleteEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class PaquetController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PaquetController.class);

	@ManagedProperty("#{coordonneeBean}")
	private CoordonneeBean coordonneeBean;

	@ManagedProperty("#{productBean}")
	private ProductBean productBean;

	@ManagedProperty("#{paquetDao}")
	private PaquetDao paquetDao;

	@ManagedProperty("#{paquetBean}")
	private PaquetBean paquetBean;

	List<Paquet> paquets;

	@PostConstruct
	public void _init() {
		PaquetController.LOGGER.debug(
				"Chargement de la liste des paquets, des produits et des coordonnées…");
		this.paquets = this.paquetDao.readAll();
	}

	// test com
	public String delete(final Paquet paquet) {
		try {
			this.paquetDao.delete(paquet.getId());
			this.paquets.remove(paquet);
			FacesMessages.info("Le paquet a été supprimé avec succès.");
		} catch (final DeleteEntityException e) {
			PaquetController.LOGGER.error(
					"Erreur lors de la suppression du paquet d'id=" + paquet.getId(), e);
			FacesMessages.error("Impossible de supprimer le paquet");
		}
		return "/views/paquet/display";
	}

	public CoordonneeBean getCoordonneeBean() {
		return this.coordonneeBean;
	}

	public PaquetBean getPaquetBean() {
		return this.paquetBean;
	}

	public PaquetDao getPaquetDao() {
		return this.paquetDao;
	}

	public List<Paquet> getPaquets() {
		return this.paquets;
	}

	public ProductBean getProductBean() {
		return this.productBean;
	}

	public String save() {
		final Paquet paquet = new Paquet();
		paquet.setColi(this.paquetBean.getProduit());
		paquet.setCoordonnee1(this.paquetBean.getExpediteur());
		paquet.setCoordonnee2(this.paquetBean.getDestinataire());
		return "/views/dashboard";
	}

	public void setCoordonneeBean(final CoordonneeBean coordonneeBean) {
		this.coordonneeBean = coordonneeBean;
	}

	public void setPaquetBean(final PaquetBean paquetBean) {
		this.paquetBean = paquetBean;
	}

	public void setPaquetDao(final PaquetDao paquetDao) {
		this.paquetDao = paquetDao;
	}

	public void setPaquets(final List<Paquet> paquets) {
		this.paquets = paquets;
	}

	public void setProductBean(final ProductBean productBean) {
		this.productBean = productBean;
	}

}
