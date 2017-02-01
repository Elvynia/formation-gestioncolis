package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.ProductBean;
import fr.formation.gestioncolis.dao.ProductDao;
import fr.formation.gestioncolis.entity.Product;
import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.DeleteEntityException;
import fr.formation.gestioncolis.exception.UpdateEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class ProductController implements Serializable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProductController.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{productBean}")
	private ProductBean productBean;

	@ManagedProperty("#{productDao}")
	private ProductDao productDao;

	/**
	 * Mémorisation de l'id du produit pour l'édition.
	 */
	private Integer productId;

	private List<Product> products;

	@PostConstruct
	public void _init() {
		ProductController.LOGGER.debug("Chargement de la liste des produits.");
		this.products = this.productDao.readAll();
	}

	public String delete(final Product product) {
		try {
			this.productDao.delete(product.getId());
			this.products.remove(product);
			FacesMessages.info("Le produit a été supprimé avec succès.");
		} catch (final DeleteEntityException e) {
			ProductController.LOGGER.error(
					"Erreur lors de la suppression du produit d'id=" + product.getId(),
					e);
			FacesMessages.error(
					"Impossible de supprimer le produit car il est utilisé dans un paquet.");
		}
		return "/views/product/display";
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return this.productId;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	/**
	 * Méthode déclenchée avant le RenderView grâce au tag f:event dans
	 * create.xhtml. On récupère l'id dans les paramètres de la requête HTTP et si
	 * il est présent on charge productBean avec les informations de la base de
	 * données.
	 */
	public void prepareEdit() {
		final String paramId = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("productId");
		if (paramId != null) {
			this.productId = Integer.parseInt(paramId);
			final Product editProduct = this.productDao.read(this.getProductId());
			this.productBean.setIntitule(editProduct.getIntitule());
			this.productBean.setPoids(editProduct.getPoids());
			this.productBean.setReference(editProduct.getReference());
		}
	}

	public String save() {
		final Product product = new Product();
		product.setIntitule(this.productBean.getIntitule());
		product.setPoids(this.productBean.getPoids());
		product.setReference(this.productBean.getReference());

		try {
			if (this.productId == null) {
				ProductController.LOGGER.debug("Création du nouveau produit {}",
						product);
				this.productDao.create(product);
				FacesMessages.info("Le produit a été créé avec succès.");
			} else {
				product.setId(this.getProductId());
				ProductController.LOGGER.debug("Mise à jour du produit {}", product);
				this.productDao.update(product);
				FacesMessages.info("Le produit a été mis à jour avec succès.");
			}
		} catch (final CreateEntityException e) {
			ProductController.LOGGER
					.error("Erreur pendant la création d'un nouveau produit.", e);
			FacesMessages.error("Impossible de créer le produit.");
		} catch (final UpdateEntityException e) {
			ProductController.LOGGER.error(
					"Erreur pendant la mise à jour du produit d'id=" + this.productId, e);
			FacesMessages.error("Impossible de mettre à jour ce produit.");
		}

		return "/views/dashboard";
	}

	/**
	 * @param productBean
	 *          the productBean to set
	 */
	public void setProductBean(final ProductBean productBean) {
		this.productBean = productBean;
	}

	/**
	 * @param productDao
	 *          the productDao to set
	 */
	public void setProductDao(final ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * @param productId
	 *          the productId to set
	 */
	public void setProductId(final Integer productId) {
		this.productId = productId;
	}
}
