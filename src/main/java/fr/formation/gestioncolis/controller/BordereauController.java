package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.BordereauBean;
import fr.formation.gestioncolis.dao.BordereauDao;
import fr.formation.gestioncolis.entity.Bordereau;

@ManagedBean
@ViewScoped
public class BordereauController implements Serializable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BordereauController.class);

	@ManagedProperty("#{bordereauBean}")
	private BordereauBean bordereauBean;

	@ManagedProperty("#{bordereauDao}")
	private BordereauDao bordereauDao;

	private List<Bordereau> bordereaux;

	@PostConstruct
	public void _init() {
		BordereauController.LOGGER
				.debug("Chargement de la liste des bordereaux.");
		this.bordereaux = this.bordereauDao.readAll();
	}

	public void detail(final Integer id) {

	}

	public List<Bordereau> getBordereaux() {
		return this.bordereaux;
	}

	public void setBordereauBean(final BordereauBean bordereauBean) {
		this.bordereauBean = bordereauBean;
	}

	public void setBordereauDao(final BordereauDao bordereauDao) {
		this.bordereauDao = bordereauDao;
	}

	public void setBordereaux(final List<Bordereau> bordereaux) {
		this.bordereaux = bordereaux;
	}
}
