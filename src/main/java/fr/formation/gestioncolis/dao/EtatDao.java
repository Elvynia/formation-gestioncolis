package fr.formation.gestioncolis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.TypedQuery;

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

	public Etat readByOrder(final Integer order) {
		final TypedQuery<Etat> query = this.em
				.createNamedQuery("Etat.findByOrder", Etat.class);
		query.setParameter("order", order);
		return query.getSingleResult();
	}
}
