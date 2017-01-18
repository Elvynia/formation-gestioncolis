package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import fr.formation.gestioncolis.dao.EtatDao;
import fr.formation.gestioncolis.entity.Commande;
import fr.formation.gestioncolis.entity.Etat;
import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.DeleteEntityException;
import fr.formation.gestioncolis.exception.UpdateEntityException;
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

	private Integer commandeId;
	private List<Commande> commandes;

	@ManagedProperty("#{commandeService}")
	private CommandeService commandeService;
	@ManagedProperty("#{createCommandeBean}")
	private CreateCommandeBean createCommandeBean;

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;
	private List<Etat> etats;

	@PostConstruct
	public void _init() {
		CommandeController.LOGGER
				.debug("Chargement de la liste des commandes.");
		this.commandes = this.commandeDao.readAll();
		this.etats = this.etatDao.readAll();
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

	public String delete(final Commande commande) {
		try {
			this.commandeDao.delete(commande.getId());
			this.commandes.remove(commande);
			FacesMessages.info("La commande a été supprimée avec succés.");
		} catch (final DeleteEntityException e) {
			CommandeController.LOGGER
					.error("Erreur lors de la suppression de la commande d'id="
							+ commande.getId(), e);
			FacesMessages.error("Impossible de supprimer la commande.");
		}
		return "/views/commande/display";
	}

	public Integer getCommandeId() {
		return this.commandeId;
	}

	public List<Commande> getCommandes() {
		return this.commandes;
	}

	public List<Etat> getEtats() {
		return this.etats;
	}

	private Commande majDatesCommande(final Commande commande) {
		final Date date = new Date();
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formater.format(date);

		if (commande.getEtatBean().getId() == 1) {
			commande.setDateCommande(date);
		} else if (commande.getEtatBean().getId() == 2) {
			commande.setDateEnvoi(date);
		} else if (commande.getEtatBean().getId() == 3) {
			commande.setAckSent(date);
		} else if (commande.getEtatBean().getId() == 4) {
			commande.setAckReceived(date);
		}

		return commande;
	}

	public String save() {
		final Commande commande = new Commande();
		commande.setDateCommande(this.commandeBean.getDateCommande());
		commande.setDateEnvoi(this.commandeBean.getDateEnvoi());
		commande.setAckSent(this.commandeBean.getAckSent());
		commande.setAckReceived(this.commandeBean.getAckReceived());

		try {
			if (this.commandeId == null) {
				CommandeController.LOGGER
						.debug("Création d'une nouvelle commande {}", commande);
				this.commandeDao.create(commande);
				FacesMessages.info("La commande a été créée avec succés.");
			} else {
				commande.setId(this.getCommandeId());
				CommandeController.LOGGER.debug("Mise à jour de la commande {}",
						commande);
				this.commandeDao.update(commande);
				FacesMessages
						.info("La commande a été mise à jour avec succés.");
			}
		} catch (final CreateEntityException e) {
			CommandeController.LOGGER.error(
					"Erreur pendant la création d'une nouvelle commande.", e);
			FacesMessages.error("Impossible de créer la commande.");
		} catch (final UpdateEntityException e) {
			CommandeController.LOGGER
					.error("Erreur pendant la mise à jour de la commande d'id="
							+ this.commandeId, e);
			FacesMessages.error("Impossible de mettre à jour cette commande.");
		}

		return "/views/dashboard";
	}

	public void setCommandeBean(final CommandeBean commandeBean) {
		this.commandeBean = commandeBean;
	}

	public void setCommandeDao(final CommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	public void setCommandeId(final Integer commandeId) {
		this.commandeId = commandeId;
	}

	public void setCommandes(final List<Commande> commandes) {
		this.commandes = commandes;
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

	public void setEtatDao(final EtatDao etatDao) {
		this.etatDao = etatDao;
	}

	public void setEtats(final List<Etat> etats) {
		this.etats = etats;
	}

	public String update(final Commande commande) {
		try {
			this.commandeDao.update(this.majDatesCommande(commande));
			CommandeController.LOGGER
					.debug("Mise à jour de la commande d'id={}", commande);
			FacesMessages.info("Mise à jour de la commande.");
		} catch (final UpdateEntityException e) {
			CommandeController.LOGGER
					.error("Erreur pendant la mise à jour de la commande d'id="
							+ this.commandeId, e);
			FacesMessages.error("Impossible de mettre à jour cette commande.");
		}
		return "/views/commande/display";
	}
}
