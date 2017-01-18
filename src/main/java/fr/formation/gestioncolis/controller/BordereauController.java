package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.BordereauBean;
import fr.formation.gestioncolis.bean.CommandeBean;
import fr.formation.gestioncolis.bean.PaquetBean;
import fr.formation.gestioncolis.bean.ProductBean;
import fr.formation.gestioncolis.dao.BordereauDao;
import fr.formation.gestioncolis.dao.EtatDao;
import fr.formation.gestioncolis.dao.PaquetDao;
import fr.formation.gestioncolis.dao.ProductDao;
import fr.formation.gestioncolis.entity.Bordereau;
import fr.formation.gestioncolis.entity.Commande;
import fr.formation.gestioncolis.entity.Etat;
import fr.formation.gestioncolis.entity.Paquet;
import fr.formation.gestioncolis.entity.Product;

@ManagedBean
@ViewScoped
public class BordereauController implements Serializable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BordereauController.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{bordereauBean}")
	private BordereauBean bordereauBean;

	@ManagedProperty("#{bordereauDao}")
	private BordereauDao bordereauDao;

	private List<Bordereau> bordereaux;

	@ManagedProperty("#{commandeBean}")
	private CommandeBean commandeBean;

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	@ManagedProperty("#{paquetBean}")
	private PaquetBean paquetBean;

	/*
	 * @ManagedProperty("#{etatBean}") private EtatBean etatBean;
	 */
	@ManagedProperty("#{paquetDao}")
	private PaquetDao paquetDao;

	@ManagedProperty("#{productBean}")
	private ProductBean productBean;

	@ManagedProperty("#{productDao}")
	private ProductDao productDao;

	@PostConstruct
	public void _init() {
		BordereauController.LOGGER
				.debug("Chargement de la liste des bordereaux.");
		this.bordereaux = this.bordereauDao.readAll();
	}

	public void detail(final Integer id) {
		final Bordereau bordereau = this.bordereauDao.read(id);
		this.bordereauBean.setId(bordereau.getId());
		this.bordereauBean.setCommande(bordereau.getCommandeBean().getId());
		this.bordereauBean.setDetail(bordereau.getDetail());
		this.bordereauBean.setDateSignature(bordereau.getDateSignature());

		final Commande commande = bordereau.getCommandeBean();
		this.commandeBean.setId(commande.getId());
		this.commandeBean.setDateCommande(commande.getDateCommande());
		this.commandeBean.setDateEnvoi(commande.getDateEnvoi());
		this.commandeBean.setAckSent(commande.getAckSent());
		this.commandeBean.setAckReceived(commande.getAckReceived());

		final Etat etat = commande.getEtatBean();
		// etat.setId(commande.getEtatBean().getId());
		// etat.setNom(this.etatDao.read(etat.getId()).getNom());
		this.commandeBean.setEtat(etat);

		final Paquet paquet = new Paquet();
		paquet.setId(commande.getPaquetBean().getId());
		paquet.setColi(this.paquetDao.read(paquet.getId()).getColi());
		this.paquetBean.setProduit(paquet.getColi());

		final Product product = new Product();
		product.setId(paquet.getColi().getId());
		product.setReference(
				this.productDao.read(product.getId()).getReference());
		this.productBean.setReference(product.getReference());
	}

	public List<Bordereau> getBordereaux() {
		return this.bordereaux;
	}

	public void setBordereauBean(final BordereauBean bordereauBean) {
		this.bordereauBean = bordereauBean;
	}

	public void setBordereauDao(final BordereauDao bordereauDao) {
		this.bordereauDao = bordereauDao;
	}

	public void setBordereaux(final List<Bordereau> bordereaux) {
		this.bordereaux = bordereaux;
	}

	public void setCommandeBean(final CommandeBean commandeBean) {
		this.commandeBean = commandeBean;
	}

	public void setEtatDao(final EtatDao etatDao) {
		this.etatDao = etatDao;
	}

	public void setPaquetBean(final PaquetBean paquetBean) {
		this.paquetBean = paquetBean;
	}

	public void setPaquetDao(final PaquetDao paquetDao) {
		this.paquetDao = paquetDao;
	}

	public void setProductBean(final ProductBean productBean) {
		this.productBean = productBean;
	}

	public void setProductDao(final ProductDao productDao) {
		this.productDao = productDao;
	}

}
