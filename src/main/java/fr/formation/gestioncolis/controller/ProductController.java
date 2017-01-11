package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.formation.gestioncolis.bean.ProductBean;
import fr.formation.gestioncolis.dao.ProductDao;
import fr.formation.gestioncolis.entity.Product;

@ManagedBean
@ViewScoped
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{productBean}")
	private ProductBean productBean;

	@ManagedProperty("#{productDao}")
	private ProductDao productDao;

	public List<Product> getProducts() {
		return this.productDao.readAll();
	}

	public String save() {
		final Product newProduct = new Product();
		newProduct.setIntitule(this.productBean.getIntitule());
		newProduct.setPoids(this.productBean.getPoids());
		newProduct.setReference(this.productBean.getReference());

		this.productDao.create(newProduct);

		return "/views/dashboard";
	}

	/**
	 * @param productBean the productBean to set
	 */
	public void setProductBean(final ProductBean productBean) {
		this.productBean = productBean;
	}

	/**
	 * @param productDao the productDao to set
	 */
	public void setProductDao(final ProductDao productDao) {
		this.productDao = productDao;
	}
}
