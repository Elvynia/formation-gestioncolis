package fr.formation.gestioncolis.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.formation.gestioncolis.dao.ProductDao;
import fr.formation.gestioncolis.entity.Product;

@ManagedBean
@ViewScoped
public class ProductController {

	@ManagedProperty("#{productDao}")
	private ProductDao dao;
	
	private List<Product> products;
	
	@PostConstruct
	public void init() {
		this.products = this.dao.readAll();
	}

	public void setDao(ProductDao dao) {
		this.dao = dao;
	}

	public List<Product> getProducts() {
		return products;
	}
}
