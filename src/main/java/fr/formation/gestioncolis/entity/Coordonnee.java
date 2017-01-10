package fr.formation.gestioncolis.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the coordonnees database table.
 * 
 */
@Entity
@Table(name="coordonnees")
@NamedQuery(name="Coordonnee.findAll", query="SELECT c FROM Coordonnee c")
public class Coordonnee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="ADDRESS_LINE1")
	private String addressLine1;

	@Column(name="ADDRESS_LINE2")
	private String addressLine2;

	private String city;

	private String country;

	private String email;

	private String firstname;

	private String lastname;

	@Column(name="PHONE_NUMBER")
	private String phoneNumber;

	@Column(name="POSTAL_CODE")
	private int postalCode;

	//bi-directional many-to-one association to Paquet
	@OneToMany(mappedBy="coordonnee1")
	private List<Paquet> paquets1;

	//bi-directional many-to-one association to Paquet
	@OneToMany(mappedBy="coordonnee2")
	private List<Paquet> paquets2;

	public Coordonnee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public List<Paquet> getPaquets1() {
		return this.paquets1;
	}

	public void setPaquets1(List<Paquet> paquets1) {
		this.paquets1 = paquets1;
	}

	public Paquet addPaquets1(Paquet paquets1) {
		getPaquets1().add(paquets1);
		paquets1.setCoordonnee1(this);

		return paquets1;
	}

	public Paquet removePaquets1(Paquet paquets1) {
		getPaquets1().remove(paquets1);
		paquets1.setCoordonnee1(null);

		return paquets1;
	}

	public List<Paquet> getPaquets2() {
		return this.paquets2;
	}

	public void setPaquets2(List<Paquet> paquets2) {
		this.paquets2 = paquets2;
	}

	public Paquet addPaquets2(Paquet paquets2) {
		getPaquets2().add(paquets2);
		paquets2.setCoordonnee2(this);

		return paquets2;
	}

	public Paquet removePaquets2(Paquet paquets2) {
		getPaquets2().remove(paquets2);
		paquets2.setCoordonnee2(null);

		return paquets2;
	}

}