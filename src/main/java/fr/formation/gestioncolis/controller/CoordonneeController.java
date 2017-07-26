package fr.formation.gestioncolis.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.CoordonneeBean;
import fr.formation.gestioncolis.dao.CoordonneeDao;
import fr.formation.gestioncolis.entity.Coordonnee;
import fr.formation.gestioncolis.exception.CreateEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class CoordonneeController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CoordonneeController.class);

	@ManagedProperty("#{coordonneeDao}")
	private CoordonneeDao dao;

	@ManagedProperty("#{coordonneeBean}")
	private CoordonneeBean bean;
	
	private List<Coordonnee> coordonnees;
	
	@PostConstruct
	public void init() {
		this.coordonnees = this.dao.readAll();
	}

	public String save() {
		final Coordonnee coord = new Coordonnee(this.bean);
		try {
			this.dao.create(coord);
			FacesMessages.info("Coordonnée créée avec succès.");
		} catch (CreateEntityException e) {
			CoordonneeController.LOGGER
					.error("Impossible de créer une nouvelle coordonnée : ", e);
			FacesMessages.error("Impossible de créer la coordonnée.");
		}
		return "/views/dashboard";
	}

	public void setDao(CoordonneeDao dao) {
		this.dao = dao;
	}

	public void setBean(CoordonneeBean bean) {
		this.bean = bean;
	}

	public List<Coordonnee> getCoordonnees() {
		return coordonnees;
	}

}
