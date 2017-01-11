package fr.formation.gestioncolis.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import fr.formation.gestioncolis.entity.Product;

@ManagedBean
@ApplicationScoped
public class ProductDao extends AbstractDao<Product> {

	@Override
	public Product read(final Integer id) {
		return this.read(Product.class, id);
	}

	@Override
	public List<Product> readAll() {
		return this.readAll(Product.class);
	}

}
