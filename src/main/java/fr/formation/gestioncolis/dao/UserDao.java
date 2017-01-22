package fr.formation.gestioncolis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestioncolis.entity.User;

@ManagedBean
@ApplicationScoped
public class UserDao extends AbstractDao<User> {

	@Override
	public User read(final Integer id) {
		return this.read(User.class, id);
	}

	@Override
	public List<User> readAll() {
		return this.readAll(User.class);
	}

}
