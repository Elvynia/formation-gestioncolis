package fr.formation.gestioncolis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the liasse database table.
 * 
 */
@Entity
@NamedQuery(name="Liasse.findAll", query="SELECT l FROM Liasse l")
public class Liasse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_SIGNATURE")
	private Date dateSignature;

	private String nature;

	private double valeur;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="COMMANDE")
	private Commande commandeBean;

	public Liasse() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateSignature() {
		return this.dateSignature;
	}

	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public double getValeur() {
		return this.valeur;
	}

	public void setValeur(double valeur) {
		this.valeur = valeur;
	}

	public Commande getCommandeBean() {
		return this.commandeBean;
	}

	public void setCommandeBean(Commande commandeBean) {
		this.commandeBean = commandeBean;
	}

}