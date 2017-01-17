package fr.formation.gestioncolis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the bordereau database table.
 * 
 */
@Entity
@Table(name = "bordereau")
@NamedQuery(name="Bordereau.findAll", query="SELECT b FROM Bordereau b")
public class Bordereau implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_SIGNATURE")
	private Date dateSignature;

	private String detail;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="COMMANDE")
	private Commande commandeBean;

	public Bordereau() {
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

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Commande getCommandeBean() {
		return this.commandeBean;
	}

	public void setCommandeBean(Commande commandeBean) {
		this.commandeBean = commandeBean;
	}

}