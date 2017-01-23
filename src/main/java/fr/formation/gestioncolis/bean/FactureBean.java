package fr.formation.gestioncolis.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.formation.gestioncolis.dao.FactureDao;
import fr.formation.gestioncolis.entity.Commande;
import fr.formation.gestioncolis.entity.Facture;

@ManagedBean
@ViewScoped
public class FactureBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{factureDao}")
	private FactureDao factureDao;
	
	private String dateFacture;
	private double montant;
	private String reference;
	private Commande commandeBean;
	private List<Facture> list;

	@PostConstruct
	public void _init() {
		this.refresh();
	}

	public void refresh() {
		this.list = this.factureDao.readAll();
		// Nouvelle notation Java 7 : Lambda.
//		Collections.sort(this.list, (o1, o2) -> Integer.parse(o1.getReference(), o2.getReference()));
		// Nouvelle notation Java 8.
		// Collections.sort(this.list, Comparator.comparingInt(Etat::getOrdre));
	}

	public List<Facture> getList() {
		return this.list;
	}

	public void setList(final List<Facture> list) {
		this.list = this.factureDao.readAll();
		// Nouvelle notation Java 7 : Lambda.
//		Collections.sort(this.list,
//				(o1, o2) -> Integer.compare(o1.getOrdre(), o2.getOrdre()));
		// Nouvelle notation Java 8.
		// Collections.sort(this.list, Comparator.comparingInt(Etat::getOrdre));
	}

	/**
	 * @return the dateFacture
	 */
	public String getDateFacture() {
		return dateFacture;
	}

	/**
	 * @return the factureDao
	 */
	public FactureDao getFactureDao() {
		return factureDao;
	}

	/**
	 * @param factureDao the factureDao to set
	 */
	public void setFactureDao(FactureDao factureDao) {
		this.factureDao = factureDao;
	}

	/**
	 * @param dateFacture
	 *            the dateFacture to set
	 */
	public void setDateFacture(final String dateFacture) {
		this.dateFacture = dateFacture;
	}

	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * @param montant
	 *            the montant to set
	 */
	public void setMontant(final double montant) {
		this.montant = montant;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(final String reference) {
		this.reference = reference;
	}

	/**
	 * @return the commandeBean
	 */
	public Commande getCommandeBean() {
		return commandeBean;
	}

	/**
	 * @param commandeBean
	 *            the commandeBean to set
	 */
	public void setCommandeBean(final Commande commandeBean) {
		this.commandeBean = commandeBean;
	}

}
