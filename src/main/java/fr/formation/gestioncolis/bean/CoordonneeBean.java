package fr.formation.gestioncolis.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CoordonneeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String country;
	private String firstname;
	private String lastname;
	private int postalCode;

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return this.addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return this.addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 * @return the postalCode
	 */
	public int getPostalCode() {
		return this.postalCode;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(final String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(final String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(final int postalCode) {
		this.postalCode = postalCode;
	}
}
