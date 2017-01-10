package fr.formation.gestioncolis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the colis database table.
 * 
 */
@Entity
@Table(name="colis")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String intitule;

	private float poids;

	private String reference;

	//bi-directional many-to-one association to Paquet
	@OneToMany(mappedBy="coli")
	private List<Paquet> paquets;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public float getPoids() {
		return this.poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<Paquet> getPaquets() {
		return this.paquets;
	}

	public void setPaquets(List<Paquet> paquets) {
		this.paquets = paquets;
	}

	public Paquet addPaquet(Paquet paquet) {
		getPaquets().add(paquet);
		paquet.setColi(this);

		return paquet;
	}

	public Paquet removePaquet(Paquet paquet) {
		getPaquets().remove(paquet);
		paquet.setColi(null);

		return paquet;
	}

}