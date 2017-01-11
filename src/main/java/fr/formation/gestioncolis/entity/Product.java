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

/**
 * The persistent class for the colis database table.
 *
 */
@Entity
@Table(name = "colis")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String intitule;

	// bi-directional many-to-one association to Paquet
	@OneToMany(mappedBy = "coli")
	private List<Paquet> paquets;

	private float poids;

	private String reference;

	public Product() {
	}

	public Paquet addPaquet(final Paquet paquet) {
		this.getPaquets().add(paquet);
		paquet.setColi(this);

		return paquet;
	}

	public Integer getId() {
		return this.id;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public List<Paquet> getPaquets() {
		return this.paquets;
	}

	public float getPoids() {
		return this.poids;
	}

	public String getReference() {
		return this.reference;
	}

	public Paquet removePaquet(final Paquet paquet) {
		this.getPaquets().remove(paquet);
		paquet.setColi(null);

		return paquet;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setIntitule(final String intitule) {
		this.intitule = intitule;
	}

	public void setPaquets(final List<Paquet> paquets) {
		this.paquets = paquets;
	}

	public void setPoids(final float poids) {
		this.poids = poids;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

}