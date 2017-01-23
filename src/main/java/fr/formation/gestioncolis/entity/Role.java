package fr.formation.gestioncolis.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the role database table.
 *
 */
@Entity
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int name;

	public Role() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public int getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(int name) {
		this.name = name;
	}

}