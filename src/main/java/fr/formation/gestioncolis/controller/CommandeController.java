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
import fr.formation.gestioncolis.dao.CommandeDao;
import fr.formation.gestioncolis.dao.EtatDao;
import fr.formation.gestioncolis.entity.Commande;
import fr.formation.gestioncolis.entity.Etat;
import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.DeleteEntityException;
import fr.formation.gestioncolis.exception.UpdateEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class CommandeController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeController.class);

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{commandeBean}")
	private CommandeBean commandeBean;

	@ManagedProperty("#{commandeDao}")
	private CommandeDao commandeDao;

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	private Integer commandeId;

	private List<Commande> commandes;

	private List<Etat> etats;

	@PostConstruct
	public void _init() {
		CommandeController.LOGGER.debug("Chargement de la liste des commandes.");
		commandes = commandeDao.readAll();
		etats = etatDao.readAll();
	}

	public String save() {
		final Commande commande = new Commande();
		commande.setDateCommande(commandeBean.getDateCommande());
		commande.setDateEnvoi(commandeBean.getDateEnvoi());
		commande.setAckSent(commandeBean.getAckSent());
		commande.setAckReceived(commandeBean.getAckReceived());

		try {
			if (commandeId == null) {
				CommandeController.LOGGER.debug("Cr�ation d'une nouvelle commande {}", commande);
				commandeDao.create(commande);
				FacesMessages.info("La commande a �t� cr��e avec succ�s.");
			} else {
				commande.setId(getCommandeId());
				CommandeController.LOGGER.debug("Mise � jour de la commande {}", commande);
				commandeDao.update(commande);
				FacesMessages.info("La commande a �t� mise � jour avec succ�s.");
			}
		} catch (final CreateEntityException e) {
			CommandeController.LOGGER.error("Erreur pendant la cr�ation d'une nouvelle commande.", e);
			FacesMessages.error("Impossible de cr�er la commande.");
		} catch (final UpdateEntityException e) {
			CommandeController.LOGGER.error("Erreur pendant la mise � jour de la commande d'id=" + commandeId, e);
			FacesMessages.error("Impossible de mettre � jour cette commande.");
		}

		return "/views/dashboard";
	}

	public String update(final Commande commande) {
		try {
			commandeDao.update(commande);
			if (commande.getEtatBean().getId() == 1) {

			}
			CommandeController.LOGGER.debug("Mise � jour de la commande d'id={}", commande);
			FacesMessages.info("Impossible de mettre � jour cette commande.");
		} catch (final UpdateEntityException e) {
			e.printStackTrace();
		}
		return "/views/commande/display";
	}

	public String delete(final Commande commande) {
		try {
			commandeDao.delete(commande.getId());
			commandes.remove(commande);
			FacesMessages.info("La commande a �t� supprim�e avec succ�s.");
		} catch (final DeleteEntityException e) {
			CommandeController.LOGGER.error("Erreur lors de la suppression de la commande d'id=" + commande.getId(), e);
			FacesMessages.error("Impossible de supprimer la commande.");
		}
		return "/views/commande/display";
	}

	public void setCommandeBean(final CommandeBean commandeBean) {
		this.commandeBean = commandeBean;
	}

	public void setCommandeDao(final CommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	public void setEtatDao(final EtatDao etatDao) {
		this.etatDao = etatDao;
	}

	public Integer getCommandeId() {
		return commandeId;
	}

	public void setCommandeId(final Integer commandeId) {
		this.commandeId = commandeId;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(final List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<Etat> getEtats() {
		return etats;
	}

	public void setEtats(final List<Etat> etats) {
		this.etats = etats;
	}

}
