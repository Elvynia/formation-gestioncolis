package fr.formation.gestioncolis.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the paquet database table.
 *
 */
@Entity
@Table(name = "paquet")
@NamedQuery(name = "Paquet.findAll", query = "SELECT p FROM Paquet p")
public class Paquet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_RECIPISSE")
	private Date dateRecipisse;

	// bi-directional many-to-one association to Commande
	@OneToMany(mappedBy = "paquetBean")
	private List<Commande> commandes;

	// bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "COLIS")
	private Product coli;

	// bi-directional many-to-one association to Coordonnee
	@ManyToOne
	@JoinColumn(name = "EXPEDITEUR")
	private Coordonnee coordonnee1;

	// bi-directional many-to-one association to Coordonnee
	@ManyToOne
	@JoinColumn(name = "DESTINATAIRE")
	private Coordonnee coordonnee2;

	public Paquet() {
	}

	public Commande addCommande(final Commande commande) {
		this.getCommandes().add(commande);
		commande.setPaquetBean(this);

		return commande;
	}

	public Product getColi() {
		return this.coli;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public Coordonnee getCoordonnee1() {
		return this.coordonnee1;
	}

	public Coordonnee getCoordonnee2() {
		return this.coordonnee2;
	}

	public Date getDateRecipisse() {
		return this.dateRecipisse;
	}

	public int getId() {
		return this.id;
	}

	public Commande removeCommande(final Commande commande) {
		this.getCommandes().remove(commande);
		commande.setPaquetBean(null);

		return commande;
	}

	public void setColi(final Product coli) {
		this.coli = coli;
	}

	public void setCommandes(final List<Commande> commandes) {
		this.commandes = commandes;
	}

	public void setCoordonnee1(final Coordonnee coordonnee1) {
		this.coordonnee1 = coordonnee1;
	}

	public void setCoordonnee2(final Coordonnee coordonnee2) {
		this.coordonnee2 = coordonnee2;
	}

	public void setDateRecipisse(final Date dateRecipisse) {
		this.dateRecipisse = dateRecipisse;
	}

	public void setId(final int id) {
		this.id = id;
	}

}