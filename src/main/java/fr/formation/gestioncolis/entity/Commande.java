package fr.formation.gestioncolis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@Table(name = "commande")
@NamedQuery(name="Commande.findAll", query="SELECT c FROM Commande c")
public class Commande implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACK_RECEIVED")
	private Date ackReceived;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACK_SENT")
	private Date ackSent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_COMMANDE")
	private Date dateCommande;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_ENVOI")
	private Date dateEnvoi;

	//bi-directional many-to-one association to Bordereau
	@OneToMany(mappedBy="commandeBean")
	private List<Bordereau> bordereaus;

	//bi-directional many-to-one association to Etat
	@ManyToOne
	@JoinColumn(name="ETAT")
	private Etat etatBean;

	//bi-directional many-to-one association to Paquet
	@ManyToOne
	@JoinColumn(name="PAQUET")
	private Paquet paquetBean;

	//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="commande")
	private List<Facture> factures;

	//bi-directional many-to-one association to Liasse
	@OneToMany(mappedBy="commandeBean")
	private List<Liasse> liasses;

	public Commande() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAckReceived() {
		return this.ackReceived;
	}

	public void setAckReceived(Date ackReceived) {
		this.ackReceived = ackReceived;
	}

	public Date getAckSent() {
		return this.ackSent;
	}

	public void setAckSent(Date ackSent) {
		this.ackSent = ackSent;
	}

	public Date getDateCommande() {
		return this.dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Date getDateEnvoi() {
		return this.dateEnvoi;
	}

	public void setDateEnvoi(Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public List<Bordereau> getBordereaus() {
		return this.bordereaus;
	}

	public void setBordereaus(List<Bordereau> bordereaus) {
		this.bordereaus = bordereaus;
	}

	public Bordereau addBordereaus(Bordereau bordereaus) {
		getBordereaus().add(bordereaus);
		bordereaus.setCommandeBean(this);

		return bordereaus;
	}

	public Bordereau removeBordereaus(Bordereau bordereaus) {
		getBordereaus().remove(bordereaus);
		bordereaus.setCommandeBean(null);

		return bordereaus;
	}

	public Etat getEtatBean() {
		return this.etatBean;
	}

	public void setEtatBean(Etat etatBean) {
		this.etatBean = etatBean;
	}

	public Paquet getPaquetBean() {
		return this.paquetBean;
	}

	public void setPaquetBean(Paquet paquetBean) {
		this.paquetBean = paquetBean;
	}

	public List<Facture> getFactures() {
		return this.factures;
	}

	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}


	public List<Liasse> getLiasses() {
		return this.liasses;
	}

	public void setLiasses(List<Liasse> liasses) {
		this.liasses = liasses;
	}

	public Liasse addLiass(Liasse liass) {
		getLiasses().add(liass);
		liass.setCommandeBean(this);

		return liass;
	}

	public Liasse removeLiass(Liasse liass) {
		getLiasses().remove(liass);
		liass.setCommandeBean(null);

		return liass;
	}

}