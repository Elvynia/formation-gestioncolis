package fr.formation.gestioncolis.controller;

import fr.formation.gestioncolis.bean.*;
import fr.formation.gestioncolis.dao.*;
import fr.formation.gestioncolis.entity.*;
import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.DeleteEntityException;
import net.bootsfaces.utils.FacesMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

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

    @ManagedProperty("#{paquetDao}")
    private PaquetDao paquetDao;

    @ManagedProperty("#{paquetBean}")
    private PaquetBean paquetBean;

    @ManagedProperty("#{productDao}")
    private ProductDao productDao;

    @ManagedProperty("#{productBean}")
    private ProductBean productBean;

    private List<Bordereau> bordereaux;

    @PostConstruct
    public void _init(){
        BordereauController.LOGGER.debug("Chargement de la liste des bordereaux.");
        this.bordereaux = bordereauDao.readAll();
    }

    public void detail(final Integer id){
        Bordereau bordereau = bordereauDao.read(id);
        this.bordereauBean.setId(bordereau.getId());
        this.bordereauBean.setCommande(bordereau.getCommandeBean());
        this.bordereauBean.setDetail(bordereau.getDetail());
        this.bordereauBean.setDateSignature(bordereau.getDateSignature());

        final Paquet paquet = new Paquet();
        paquet.setId(bordereauBean.getCommande().getPaquetBean().getId());
        paquet.setColi(this.paquetDao.read(paquet.getId()).getColi());
        this.paquetBean.setProduit(paquet.getColi());

        final Product product = new Product();
        product.setId(paquet.getColi().getId());
        product.setReference(this.productDao.read(product.getId()).getReference());
        this.productBean.setReference(product.getReference());
    }

    public String create(){
        Bordereau bordereau = new Bordereau();
        Commande commande = new Commande();
        commande.setId(this.bordereauBean.getCommande().getId());
        bordereau.setCommandeBean(commande);
        bordereau.setDetail(this.bordereauBean.getDetail());
        bordereau.setDateSignature(this.bordereauBean.getDateSignature());
        try {
            this.bordereauDao.create(bordereau);
        } catch (CreateEntityException e) {
            LOGGER.error("Impossible de créer le bordereau");
        }
        return "/views/bordereau/display";
    }

	public String delete(final String id) {
		try {
			this.bordereauDao.delete(Integer.parseInt(id));
			this.bordereaux.remove(bordereauDao.read(Integer.parseInt(id)));
			FacesMessages.info("Le bordereau a été supprimé avec succès.");
		} catch (final DeleteEntityException e) {
			BordereauController.LOGGER
					.error("Erreur lors de la suppression du bordereau d'id="
							+ id, e);
			FacesMessages.error("Impossible de supprimer le bordereau.");
		}
		return "/views/bordereau/display";
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

    public void setPaquetBean(PaquetBean paquetBean) {
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