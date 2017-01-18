package fr.formation.gestioncolis.controller;

import fr.formation.gestioncolis.bean.*;
import fr.formation.gestioncolis.dao.*;
import fr.formation.gestioncolis.entity.*;
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

    @ManagedProperty("#{bordereauBean}")
    private BordereauBean bordereauBean;

    @ManagedProperty("#{bordereauDao}")
    private BordereauDao bordereauDao;

    @ManagedProperty("#{commandeBean}")
    private CommandeBean commandeBean;

    @ManagedProperty("#{etatDao}")
    private EtatDao etatDao;

    /*@ManagedProperty("#{etatBean}")
    private EtatBean etatBean;
*/
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

    public void detail(Integer id){
        Bordereau bordereau = bordereauDao.read(id);
        this.bordereauBean.setId(bordereau.getId());
        this.bordereauBean.setCommande(bordereau.getCommandeBean().getId());
        this.bordereauBean.setDetail(bordereau.getDetail());
        this.bordereauBean.setDateSignature(bordereau.getDateSignature());

        Commande commande = bordereau.getCommandeBean();
        this.commandeBean.setId(commande.getId());
        this.commandeBean.setDateCommande(commande.getDateCommande());
        this.commandeBean.setDateEnvoi(commande.getDateEnvoi());
        this.commandeBean.setAckSent(commande.getAckSent());
        this.commandeBean.setAckReceived(commande.getAckReceived());

        Etat etat = commande.getEtatBean();
       // etat.setId(commande.getEtatBean().getId());
       // etat.setNom(this.etatDao.read(etat.getId()).getNom());
        this.commandeBean.setEtat(etat);

        Paquet paquet = new Paquet();
        paquet.setId(commande.getPaquetBean().getId());
        paquet.setColi(this.paquetDao.read(paquet.getId()).getColi());
        this.paquetBean.setProduit(paquet.getColi());

        Product product = new Product();
        product.setId(paquet.getColi().getId());
        product.setReference(this.productDao.read(product.getId()).getReference());
        this.productBean.setReference(product.getReference());
    }

    public List<Bordereau> getBordereaux() {
        return bordereaux;
    }

    public void setBordereauBean(BordereauBean bordereauBean) {
        this.bordereauBean = bordereauBean;
    }

    public void setBordereauDao(BordereauDao bordereauDao) {
        this.bordereauDao = bordereauDao;
    }

    public void setBordereaux(List<Bordereau> bordereaux) {
        this.bordereaux = bordereaux;
    }

    public void setCommandeBean(CommandeBean commandeBean) {
        this.commandeBean = commandeBean;
    }

    public void setPaquetBean(PaquetBean paquetBean) {
        this.paquetBean = paquetBean;
    }

    public void setPaquetDao(PaquetDao paquetDao) {
        this.paquetDao = paquetDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public void setEtatDao(EtatDao etatDao) {
        this.etatDao = etatDao;
    }


}
