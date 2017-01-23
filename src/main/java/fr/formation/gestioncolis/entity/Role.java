package fr.formation.gestioncolis.entity;

import java.io.Serializable;

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
		
	}

	public int getId() {
		return this.id;
	}

	public int getNom() {
		return this.nom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(int nom) {
		this.nom = nom;
	}
}
