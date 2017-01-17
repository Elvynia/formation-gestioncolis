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
import fr.formation.gestioncolis.bean.ProductBean;
import fr.formation.gestioncolis.dao.CoordonneeDao;
import fr.formation.gestioncolis.dao.PaquetDao;
import fr.formation.gestioncolis.dao.ProductDao;
import fr.formation.gestioncolis.entity.Coordonnee;
import fr.formation.gestioncolis.entity.Paquet;
import fr.formation.gestioncolis.entity.Product;

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

	@ManagedProperty("#{coordonneeDao}")
	private CoordonneeDao coordonneeDao;

	@ManagedProperty("#{productDao}")
	private ProductDao productDao;

	@ManagedProperty("#{paquetDao}")
	private PaquetDao paquetDao;

	List<Paquet> paquets;

	List<Product> products;

	List<Coordonnee> coordonnees;

	@PostConstruct
	public void _init() {
		PaquetController.LOGGER.debug(
				"Chargement de la liste des paquets, des produits et des coordonnées…");
		this.paquets = this.paquetDao.readAll();
		this.products = this.productDao.readAll();
		this.coordonnees = this.coordonneeDao.readAll();
	}

	public CoordonneeBean getCoordonneeBean() {
		return this.coordonneeBean;
	}

	public CoordonneeDao getCoordonneeDao() {
		return this.coordonneeDao;
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

	public ProductDao getProductDao() {
		return this.productDao;
	}

	public void setCoordonneeBean(final CoordonneeBean coordonneeBean) {
		this.coordonneeBean = coordonneeBean;
	}

	public void setCoordonneeDao(final CoordonneeDao coordonneeDao) {
		this.coordonneeDao = coordonneeDao;
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

	public void setProductDao(final ProductDao productDao) {
		this.productDao = productDao;
	}

}
