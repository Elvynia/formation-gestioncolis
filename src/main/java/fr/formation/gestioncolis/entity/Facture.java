package fr.formation.gestioncolis.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the facture database table.
 * 
 */
@Entity
@NamedQuery(name="Facture.findAll", query="SELECT f FROM Facture f")
public class Facture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="DATE_FACTURE")
	private String dateFacture;

	private double montant;

	private String reference;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="COMMANDE")
	private Commande commandeBean;

	public Facture() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateFacture() {
		return this.dateFacture;
	}

	public void setDateFacture(String dateFacture) {
		this.dateFacture = dateFacture;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Commande getCommandeBean() {
		return this.commandeBean;
	}

	public void setCommandeBean(Commande commandeBean) {
		this.commandeBean = commandeBean;
	}

}