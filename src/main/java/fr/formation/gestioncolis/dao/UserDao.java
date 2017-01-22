package fr.formation.gestioncolis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.TypedQuery;

import fr.formation.gestioncolis.entity.User;

@ManagedBean
@ApplicationScoped
public class UserDao extends AbstractDao<User> {

	// private static UserDao INSTANCE;
	//
	// public static UserDao getInstance() {
	// return UserDao.INSTANCE;
	// }
	//
	// @PostConstruct
	// public void _init() {
	// UserDao.INSTANCE = this;
	// LoggerFactory.getLogger(this.getClass()).info("-----DAO INIT");
	// }

	@Override
	public User read(final Integer id) {
		return this.read(User.class, id);
	}

	@Override
	public List<User> readAll() {
		return this.readAll(User.class);
	}

	public User readByUsername(final String username) {
		final TypedQuery<User> query = this.em
				.createNamedQuery("User.findByUsername", User.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}

}
