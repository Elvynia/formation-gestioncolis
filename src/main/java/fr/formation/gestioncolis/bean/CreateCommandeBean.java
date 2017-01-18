package fr.formation.gestioncolis.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CreateCommandeBean {

	private Integer coordonneeId;

	private Integer productId;

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return this.productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(final Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the coordonneeId
	 */
	public Integer getCoordonneeId() {
		return coordonneeId;
	}

	/**
	 * @param coordonneeId the coordonneeId to set
	 */
	public void setCoordonneeId(Integer coordonneeId) {
		this.coordonneeId = coordonneeId;
	}
}
