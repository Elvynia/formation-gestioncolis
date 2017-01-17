package fr.formation.gestioncolis.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@ViewScoped
public class CommandeBean implements Serializable {

    private Integer id;
    private Integer paquet;
    private Date dateCommande;
    private Date dateEnvoi;
    private Integer etat;
    private Date ackSent;
    private Date ackReceived;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaquet() {
        return paquet;
    }

    public void setPaquet(Integer paquet) {
        this.paquet = paquet;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Date getAckSent() {
        return ackSent;
    }

    public void setAckSent(Date ackSent) {
        this.ackSent = ackSent;
    }

    public Date getAckReceived() {
        return ackReceived;
    }

    public void setAckReceived(Date ackReceived) {
        this.ackReceived = ackReceived;
    }
}
