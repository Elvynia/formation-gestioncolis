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

	private Date ackReceived;

	private Date ackSent;

	private Date dateCommande;

	private Date dateEnvoi;

	private Etat etat;

	private Integer id;

	private Integer idPaquet;

	public Date getAckReceived() {
		return this.ackReceived;
	}

	public Date getAckSent() {
		return this.ackSent;
	}

	public Date getDateCommande() {
		return this.dateCommande;
	}

	public Date getDateEnvoi() {
		return this.dateEnvoi;
	}

	public Etat getEtat() {
		return this.etat;
	}

	public Integer getIdPaquet() {
		return this.idPaquet;
	}

	public void setAckReceived(final Date ackReceived) {
		this.ackReceived = ackReceived;
	}

	public void setAckSent(final Date ackSent) {
		this.ackSent = ackSent;
	}

	public void setDateCommande(final Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public void setDateEnvoi(final Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public void setEtat(final Etat etat) {
		this.etat = etat;
	}

	public void setIdPaquet(final Integer idPaquet) {
		this.idPaquet = idPaquet;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
