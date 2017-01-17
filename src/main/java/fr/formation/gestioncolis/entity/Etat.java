package fr.formation.gestioncolis.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the etat database table.
 *
 */
@Entity
@Table(name = "etat")
@NamedQuery(name = "Etat.findAll", query = "SELECT e FROM Etat e")
public class Etat implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Commande
	@OneToMany(mappedBy = "etatBean")
	private List<Commande> commandes;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;

	private int ordre;

	@Transient
	private String test;

	public Etat() {
	}

	public Etat(final String nom) {
		this.nom = nom;
	}

	public Commande addCommande(final Commande commande) {
		this.getCommandes().add(commande);
		commande.setEtatBean(this);

		return commande;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public int getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * @return the ordre
	 */
	public int getOrdre() {
		return this.ordre;
	}

	public Commande removeCommande(final Commande commande) {
		this.getCommandes().remove(commande);
		commande.setEtatBean(null);

		return commande;
	}

	public void setCommandes(final List<Commande> commandes) {
		this.commandes = commandes;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	/**
	 * @param ordre
	 *          the ordre to set
	 */
	public void setOrdre(final int ordre) {
		this.ordre = ordre;
	}

}