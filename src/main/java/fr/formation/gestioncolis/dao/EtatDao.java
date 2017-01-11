package fr.formation.gestioncolis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestioncolis.entity.Etat;

@ManagedBean
@ApplicationScoped
public class EtatDao extends AbstractDao<Etat> {

	@Override
	public Etat read(final Integer id) {
		return this.read(Etat.class, id);
	}

	@Override
	public List<Etat> readAll() {
		return this.readAll(Etat.class);
	}

}
