package fr.formation.gestioncolis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestioncolis.entity.Coordonnee;

@ManagedBean
@ApplicationScoped
public class CoordonneeDao extends AbstractDao<Coordonnee> {

	@Override
	public Coordonnee read(final Integer id) {
		return this.read(Coordonnee.class, id);
	}

	@Override
	public List<Coordonnee> readAll() {
		return this.readAll(Coordonnee.class);
	}

}
