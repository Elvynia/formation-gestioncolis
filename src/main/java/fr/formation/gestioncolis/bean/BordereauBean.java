package fr.formation.gestioncolis.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@ViewScoped
public class BordereauBean implements Serializable{

    private Integer id;
    private Integer commande;
    private String detail;
    private Date dateSignature;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommande() {
        return commande;
    }

    public void setCommande(Integer commande) {
        this.commande = commande;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(Date dateSignature) {
        this.dateSignature = dateSignature;
    }
}
