package fr.formation.gestioncolis.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.formation.gestioncolis.bean.CreateCommandeBean;
import fr.formation.gestioncolis.dao.CommandeDao;
import fr.formation.gestioncolis.entity.Commande;
import fr.formation.gestioncolis.service.CommandeService;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class CommandeController {

	@ManagedProperty("#{commandeDao}")
	private CommandeDao dao;

	@ManagedProperty("#{commandeService}")
	private CommandeService service;

	@ManagedProperty("#{createCommandeBean}")
	private CreateCommandeBean createBean;

	private List<Commande> commandes;

	@PostConstruct
	public void init() {
		this.commandes = this.dao.readAll();
	}

	public void create() {
		if (this.service.create(this.createBean.getProductId(),
				this.createBean.getCoordonneeId())) {
			FacesMessages.info("Commande effectuée avec succès.");
			this.createBean.setProductId(null);
			this.createBean.setCoordonneeId(null);
			this.commandes = this.dao.readAll();
		} else {
			FacesMessages.error("Impossible d'effectuer cette commande.");
		}
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setDao(CommandeDao dao) {
		this.dao = dao;
	}

	public void setService(CommandeService service) {
		this.service = service;
	}

	public void setCreateBean(CreateCommandeBean createBean) {
		this.createBean = createBean;
	}
}
