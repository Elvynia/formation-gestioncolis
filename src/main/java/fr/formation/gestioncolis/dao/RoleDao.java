package fr.formation.gestioncolis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestioncolis.entity.Role;

@ManagedBean
@ApplicationScoped
public class RoleDao extends AbstractDao<Role> {

	@Override
	public Role read(final Integer id) {
		return this.read(Role.class, id);
	}

	@Override
	public List<Role> readAll() {
		return this.readAll(Role.class);
	}

}
