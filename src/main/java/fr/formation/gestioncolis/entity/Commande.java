package fr.formation.gestioncolis.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
 * The persistent class for the commande database table.
 *
 */
@Entity
@Table(name = "commande")
@NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
public class Commande implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACK_RECEIVED")
	private Date ackReceived;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACK_SENT")
	private Date ackSent;

	// bi-directional many-to-one association to Bordereau
	@OneToMany(mappedBy = "commandeBean")
	private List<Bordereau> bordereaus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_COMMANDE")
	private Date dateCommande;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_ENVOI")
	private Date dateEnvoi;

	// bi-directional many-to-one association to Etat
	@ManyToOne
	@JoinColumn(name = "ETAT")
	private Etat etatBean;

	// bi-directional many-to-one association to Facture
	@OneToMany(mappedBy = "commandeBean")
	private List<Facture> factures;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// bi-directional many-to-one association to Liasse
	@OneToMany(mappedBy = "commandeBean")
	private List<Liasse> liasses;

	// bi-directional many-to-one association to Paquet
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PAQUET")
	private Paquet paquetBean;

	public Commande() {
	}

	public Bordereau addBordereaus(final Bordereau bordereaus) {
		this.getBordereaus().add(bordereaus);
		bordereaus.setCommandeBean(this);

		return bordereaus;
	}

	public Facture addFacture(final Facture facture) {
		this.getFactures().add(facture);
		facture.setCommandeBean(this);

		return facture;
	}

	public Liasse addLiass(final Liasse liass) {
		this.getLiasses().add(liass);
		liass.setCommandeBean(this);

		return liass;
	}

	public Date getAckReceived() {
		return this.ackReceived;
	}

	public Date getAckSent() {
		return this.ackSent;
	}

	public List<Bordereau> getBordereaus() {
		return this.bordereaus;
	}

	public Date getDateCommande() {
		return this.dateCommande;
	}

	public Date getDateEnvoi() {
		return this.dateEnvoi;
	}

	public Etat getEtatBean() {
		return this.etatBean;
	}

	public List<Facture> getFactures() {
		return this.factures;
	}

	public int getId() {
		return this.id;
	}

	public List<Liasse> getLiasses() {
		return this.liasses;
	}

	public Paquet getPaquetBean() {
		return this.paquetBean;
	}

	public Bordereau removeBordereaus(final Bordereau bordereaus) {
		this.getBordereaus().remove(bordereaus);
		bordereaus.setCommandeBean(null);

		return bordereaus;
	}

	public Facture removeFacture(final Facture facture) {
		this.getFactures().remove(facture);
		facture.setCommandeBean(null);

		return facture;
	}

	public Liasse removeLiass(final Liasse liass) {
		this.getLiasses().remove(liass);
		liass.setCommandeBean(null);

		return liass;
	}

	public void setAckReceived(final Date ackReceived) {
		this.ackReceived = ackReceived;
	}

	public void setAckSent(final Date ackSent) {
		this.ackSent = ackSent;
	}

	public void setBordereaus(final List<Bordereau> bordereaus) {
		this.bordereaus = bordereaus;
	}

	public void setDateCommande(final Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public void setDateEnvoi(final Date dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public void setEtatBean(final Etat etatBean) {
		this.etatBean = etatBean;
	}

	public void setFactures(final List<Facture> factures) {
		this.factures = factures;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setLiasses(final List<Liasse> liasses) {
		this.liasses = liasses;
	}

	public void setPaquetBean(final Paquet paquetBean) {
		this.paquetBean = paquetBean;
	}

}