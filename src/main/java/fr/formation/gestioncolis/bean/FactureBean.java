package fr.formation.gestioncolis.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class FactureBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dateFacture;
	private double montant;
	private String reference;
	private Integer commande;
	/**
	 * @return the dateFacture
	 */
	public String getDateFacture() {
		return dateFacture;
	}
	/**
	 * @param dateFacture the dateFacture to set
	 */
	public void setDateFacture(String dateFacture) {
		this.dateFacture = dateFacture;
	}
	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}
	/**
	 * @param montant the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}
	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return the commandeBean
	 */
	public Integer getCommande() {
		return commande;
	}
	/**
	 * @param commandeBean the commandeBean to set
	 */
	public void setCommande(Integer commande) {
		this.commande = commande;
	}
	

}