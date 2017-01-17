package fr.formation.gestioncolis.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import fr.formation.gestioncolis.entity.Etat;

@ManagedBean
@ViewScoped
public class CommandeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dateCommande;

	private Date dateEnvoi;

	private Date ackSent;

	private Date ackReceived;

	private Etat etat;

	private Integer idPaquet;

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(final Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Date getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(final Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public Date getAckSent() {
		return ackSent;
	}

	public void setAckSent(final Date ackSent) {
		this.ackSent = ackSent;
	}

	public Date getAckReceived() {
		return ackReceived;
	}

	public void setAckReceived(final Date ackReceived) {
		this.ackReceived = ackReceived;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(final Etat etat) {
		this.etat = etat;
	}

	public Integer getIdPaquet() {
		return idPaquet;
	}

	public void setIdPaquet(final Integer idPaquet) {
		this.idPaquet = idPaquet;
	}
}
