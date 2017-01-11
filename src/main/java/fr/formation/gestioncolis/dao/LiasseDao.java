package fr.formation.gestioncolis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestioncolis.entity.Liasse;

@ManagedBean
@ApplicationScoped
public class LiasseDao extends AbstractDao<Liasse> {

	@Override
	public Liasse read(final Integer id) {
		return this.read(Liasse.class, id);
	}

	@Override
	public List<Liasse> readAll() {
		return this.readAll(Liasse.class);
	}

}
