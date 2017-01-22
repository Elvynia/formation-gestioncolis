package fr.formation.gestioncolis.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import fr.formation.gestioncolis.entity.Commande;

@ManagedBean
@ViewScoped
public class BordereauBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Commande commande;
	private Date dateSignature;
	private String detail;
	private Integer id;

	public Commande getCommande() {
		return this.commande;
	}

	public Date getDateSignature() {
		return this.dateSignature;
	}

	public String getDetail() {
		return this.detail;
	}

	public Integer getId() {
		return this.id;
	}

	public void setCommande(final Commande commande) {
		this.commande = commande;
	}

	public void setDateSignature(final Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public void setDetail(final String detail) {
		this.detail = detail;
	}

	public void setId(final Integer id) {
		this.id = id;
	}
}
