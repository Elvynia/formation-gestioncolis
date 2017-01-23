package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.LiasseBean;
import fr.formation.gestioncolis.dao.LiasseDao;
import fr.formation.gestioncolis.entity.Liasse;

@ManagedBean
@ViewScoped
public class LiasseController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(EtatController.class);

	private static final long serialVersionUID = 1L;

	private Integer liasseId;

	// Injection de dépendance ;-) Merci Jérémy
	@ManagedProperty("#{liasseBean}")
	private LiasseBean liasseBean;

	@ManagedProperty("#{liasseDao}")
	private LiasseDao liasseDao;

	private List<Liasse> liasses;
	
	private List<String> selectNature;

	@PostConstruct
	public void _init() {
		LiasseController.LOGGER.debug("Chargement de la liste des liasses");
		this.liasses = this.liasseDao.readAll();
		this.selectNature = new ArrayList<>();
		this.selectNature.add("Cadeaux");
		this.selectNature.add("Document");
		this.selectNature.add("Echantillons commercial");
		this.selectNature.add("Retour marchandise");
		this.selectNature.add("Autres...");
	}
	
	
	/**
	 *
	 * LISTE DES GETTERS SETTERS
	 */

	public LiasseBean getLiasseBean() {
		return this.liasseBean;
	}

	public Integer getLiasseId() {
		return this.liasseId;
	}

	public void setLiasseId(final Integer liasseId) {
		this.liasseId = liasseId;
	}

	public void setLiasseBean(final LiasseBean liasseBean) {
		this.liasseBean = liasseBean;
	}

	public LiasseDao getLiasseDao() {
		return this.liasseDao;
	}

	public void setLiasseDao(final LiasseDao liasseDao) {
		this.liasseDao = liasseDao;
	}

	public List<Liasse> getLiasses() {
		return this.liasses;
	}

	public void setLiasses(final List<Liasse> liasses) {
		this.liasses = liasses;
	}


	public List<String> getSelectNature() {
		return this.selectNature;
	}


	public void setSelectNature(List<String> selectNature) {
		this.selectNature = selectNature;
	}

}
