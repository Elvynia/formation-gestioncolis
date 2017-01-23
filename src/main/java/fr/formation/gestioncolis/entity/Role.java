package fr.formation.gestioncolis.entity;

import java.io.Serializable;
<<<<<<< HEAD

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int nom;
	
	public Role() {
		
=======
import javax.persistence.*;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int nom;

	public Role() {
>>>>>>> 399f582830eef3fcb45ef246facb0e83140f2453
	}

	public int getId() {
		return this.id;
	}

<<<<<<< HEAD
	public int getNom() {
		return this.nom;
	}

=======
>>>>>>> 399f582830eef3fcb45ef246facb0e83140f2453
	public void setId(int id) {
		this.id = id;
	}

<<<<<<< HEAD
	public void setNom(int nom) {
		this.nom = nom;
	}
}
=======
	public int getNom() {
		return this.nom;
	}

	public void setNom(int nom) {
		this.nom = nom;
	}

}
>>>>>>> 399f582830eef3fcb45ef246facb0e83140f2453
