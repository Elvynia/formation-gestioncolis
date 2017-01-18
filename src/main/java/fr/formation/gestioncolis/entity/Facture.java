package fr.formation.gestioncolis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the facture database table.
 *
 */
@Entity
@Table(name = "facture")
@NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f")
public class Facture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "DATE_FACTURE")
	private String dateFacture;

	private double montant;

	private String reference;

	// bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name = "COMMANDE")
	private Commande commande;

	public Facture() {
	}

	public Commande getCommande() {
		return this.commande;
	}

	public String getDateFacture() {
		return this.dateFacture;
	}

	public int getId() {
		return this.id;
	}

	public double getMontant() {
		return this.montant;
	}

	public String getReference() {
		return this.reference;
	}

	public void setCommande(final Commande commande) {
		this.commande = commande;
	}

	public void setDateFacture(final String dateFacture) {
		this.dateFacture = dateFacture;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setMontant(final double montant) {
		this.montant = montant;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

}