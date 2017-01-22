package fr.formation.gestioncolis.bean;

import java.io.Serializable;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LiasseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private CommandeBean commandeBean;

	private double valeur;

	private String nature;

	private Date dateSignature;

	/**
	 * GETTER SETTER ---------------- //
	 *
	 */

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public CommandeBean getCommandeBean() {
		return this.commandeBean;
	}

	public void setCommandeBean(final CommandeBean commandeBean) {
		this.commandeBean = commandeBean;
	}

	public double getValeur() {
		return this.valeur;
	}

	public void setValeur(final double valeur) {
		this.valeur = valeur;
	}

	public String getNature() {
		return this.nature;
	}

	public void setNature(final String nature) {
		this.nature = nature;
	}

	public Date getDateSignature() {
		return this.dateSignature;
	}

	public void setDateSignature(final Date dateSignature) {
		this.dateSignature = dateSignature;
	}

}
