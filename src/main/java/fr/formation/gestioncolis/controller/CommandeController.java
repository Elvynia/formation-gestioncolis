package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.CommandeBean;
import fr.formation.gestioncolis.bean.CreateCommandeBean;
import fr.formation.gestioncolis.dao.CommandeDao;
import fr.formation.gestioncolis.entity.Commande;
import fr.formation.gestioncolis.service.CommandeService;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class CommandeController implements Serializable {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CommandeController.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{commandeBean}")
	private CommandeBean commandeBean;

	@ManagedProperty("#{commandeDao}")
	private CommandeDao commandeDao;

	private List<Commande> commandes;

	@ManagedProperty("#{commandeService}")
	private CommandeService commandeService;

	@ManagedProperty("#{createCommandeBean}")
	private CreateCommandeBean createCommandeBean;

	@PostConstruct
	public void _init() {
		CommandeController.LOGGER
				.debug("Chargment des commandes depuis la BDD.");
		this.commandes = this.commandeDao.readAll();
	}

	public String create() {
		if (this.commandeService.create(this.createCommandeBean.getProductId(),
				this.createCommandeBean.getCoordonneeId())) {
			FacesMessages.info("Commande enregistrée avec succès.");
		} else {
			FacesMessages
					.error("Impossible d'enregistrer la nouvelle commande.");
		}
		return "/views/dashboard";
	}

	/**
	 * @return the commandes
	 */
	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandeBean(final CommandeBean commandeBean) {
		this.commandeBean = commandeBean;
	}

	public void setCommandeDao(final CommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	/**
	 * @param commandeService the commandeService to set
	 */
	public void setCommandeService(final CommandeService commandeService) {
		this.commandeService = commandeService;
	}

	/**
	 * @param createCommandeBean the createCommandeBean to set
	 */
	public void setCreateCommandeBean(
			final CreateCommandeBean createCommandeBean) {
		this.createCommandeBean = createCommandeBean;
	}
}
