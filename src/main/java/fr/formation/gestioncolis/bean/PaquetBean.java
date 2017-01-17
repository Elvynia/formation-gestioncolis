package fr.formation.gestioncolis.bean;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import fr.formation.gestioncolis.entity.Coordonnee;
import fr.formation.gestioncolis.entity.Product;

@ManagedBean
@ViewScoped
public class PaquetBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Product produit;
	private Coordonnee expediteur;
	private Coordonnee destinataire;
	private Calendar dateRecepice;

	public Calendar getDateRecepice() {
		return this.dateRecepice;
	}

	public Coordonnee getDestinataire() {
		return this.destinataire;
	}

	public Coordonnee getExpediteur() {
		return this.expediteur;
	}

	public Integer getId() {
		return this.id;
	}

	public Product getProduit() {
		return this.produit;
	}

	public void setDateRecepice(final Calendar dateRecepice) {
		this.dateRecepice = dateRecepice;
	}

	public void setDestinataire(final Coordonnee destinataire) {
		this.destinataire = destinataire;
	}

	public void setExpediteur(final Coordonnee expediteur) {
		this.expediteur = expediteur;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setProduit(final Product produit) {
		this.produit = produit;
	}

}
