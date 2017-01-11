package fr.formation.gestioncolis.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String intitule;

	private float poids;

	private String reference;

	/**
	 * @return the intitule
	 */
	public String getIntitule() {
		return this.intitule;
	}

	/**
	 * @return the poids
	 */
	public float getPoids() {
		return this.poids;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return this.reference;
	}

	/**
	 * @param intitule the intitule to set
	 */
	public void setIntitule(final String intitule) {
		this.intitule = intitule;
	}

	/**
	 * @param poids the poids to set
	 */
	public void setPoids(final float poids) {
		this.poids = poids;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(final String reference) {
		this.reference = reference;
	}

}
