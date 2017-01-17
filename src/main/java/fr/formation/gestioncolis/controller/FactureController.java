package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.FactureBean;
import fr.formation.gestioncolis.dao.FactureDao;
import fr.formation.gestioncolis.entity.Facture;

@ManagedBean
@ViewScoped
public class FactureController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(FactureController.class);

	private static final long serialVersionUID = 1L;

	/**
	 * Mémorisation de l'id da la facture pour l'édition.
	 */
	private Integer factureId;
	@ManagedProperty("#{factureDao}")
	private FactureDao factureDao;
	@ManagedProperty("#{factureBean}")
	private FactureBean factureBean;

	private List<Facture> factures;

	@PostConstruct
	public void _init() {
		FactureController.LOGGER.debug("Chargement de la liste des factures.");
		this.factures = this.factureDao.readAll();
	}

	/**
	 * @return the factureId
	 */
	public Integer getFactureId() {
		return this.factureId;
	}

	/**
	 * @param factureId
	 *            the factureId to set
	 */
	public void setFactureId(final Integer factureId) {
		this.factureId = factureId;
	}

	/**
	 * @param factureBean
	 *            the factureBean to set
	 */
	public void setFactureBean(final FactureBean factureBean) {
		this.factureBean = factureBean;
	}

	/**
	 * @param factureDao
	 *            the factureDao to set
	 */
	public void setFactureDao(final FactureDao factureDao) {
		this.factureDao = factureDao;
	}

	public List<Facture> getFactures() {
		return this.factures;
	}
}
