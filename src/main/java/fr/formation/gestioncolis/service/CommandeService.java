package fr.formation.gestioncolis.service;

import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import fr.formation.gestioncolis.dao.CommandeDao;
import fr.formation.gestioncolis.dao.CoordonneeDao;
import fr.formation.gestioncolis.dao.PaquetDao;
import fr.formation.gestioncolis.dao.ProductDao;
import fr.formation.gestioncolis.entity.Commande;
import fr.formation.gestioncolis.entity.Paquet;
import fr.formation.gestioncolis.exception.CreateEntityException;

@ManagedBean
@ApplicationScoped
public class CommandeService {

	@ManagedProperty("#{commandeDao}")
	private CommandeDao commandeDao;

	@ManagedProperty("#{coordonneeDao}")
	private CoordonneeDao coordonneeDao;

	@ManagedProperty("#{etatService}")
	private EtatService etatService;

	@ManagedProperty("#{paquetDao}")
	private PaquetDao paquetDao;

	@ManagedProperty("#{productDao}")
	private ProductDao productDao;

	public boolean create(final Integer productId, final Integer coordonneeId) {
		final Commande commande = new Commande();
		commande.setDateCommande(new Date());
		final Paquet paquet = new Paquet();
		paquet.setColi(this.productDao.read(productId));
		paquet.setCoordonnee1(this.coordonneeDao.read(coordonneeId));
		paquet.setCoordonnee2(this.coordonneeDao.read(coordonneeId));
		commande.setPaquetBean(paquet);
		commande.setEtatBean(this.etatService.getFirst());
		try {
			this.commandeDao.create(commande);
			return true;
		} catch (final CreateEntityException e) {
			return false;
		}
	}

	/**
	 * @param commandeDao
	 *            the commandeDao to set
	 */
	public void setCommandeDao(final CommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	/**
	 * @param coordonneeDao
	 *            the coordonneeDao to set
	 */
	public void setCoordonneeDao(final CoordonneeDao coordonneeDao) {
		this.coordonneeDao = coordonneeDao;
	}

	/**
	 * @param etatService
	 *            the etatService to set
	 */
	public void setEtatService(final EtatService etatService) {
		this.etatService = etatService;
	}

	/**
	 * @param paquetDao
	 *            the paquetDao to set
	 */
	public void setPaquetDao(final PaquetDao paquetDao) {
		this.paquetDao = paquetDao;
	}

	/**
	 * @param productDao
	 *            the productDao to set
	 */
	public void setProductDao(final ProductDao productDao) {
		this.productDao = productDao;
	}
}
