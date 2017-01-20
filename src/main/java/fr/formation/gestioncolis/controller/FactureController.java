package fr.formation.gestioncolis.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.formation.gestioncolis.bean.CommandeBean;
import fr.formation.gestioncolis.bean.EtatBean;
import fr.formation.gestioncolis.bean.FactureBean;
import fr.formation.gestioncolis.bean.ListCommandesBean;
import fr.formation.gestioncolis.dao.CommandeDao;
import fr.formation.gestioncolis.dao.EtatDao;
import fr.formation.gestioncolis.dao.FactureDao;
import fr.formation.gestioncolis.entity.Commande;
import fr.formation.gestioncolis.entity.Etat;
import fr.formation.gestioncolis.entity.Facture;
import fr.formation.gestioncolis.exception.CreateEntityException;
import fr.formation.gestioncolis.exception.DeleteEntityException;
import fr.formation.gestioncolis.exception.UpdateEntityException;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean
@ViewScoped
public class FactureController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(FactureController.class);

	private static final long serialVersionUID = 1L;

	/**
	 * Mémorisation de l'id da la facture pour l'édition.
	 */
	private Integer factureId;

	@ManagedProperty("#{factureBean}")
	private FactureBean factureBean;

	@ManagedProperty("#{commandeBean}")
	private CommandeBean commandeBean;

	@ManagedProperty("#{factureDao}")
	private FactureDao factureDao;

	@ManagedProperty("#{etatBean}")
	private EtatBean etatBean;

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	@ManagedProperty("#{commandeDao}")
	private CommandeDao commandeDao;

	private List<Facture> factures;
	/**
	 * id de la commande sélectionnée lors de la création d'une facture
	 */
	private int commandeId;

	@ManagedProperty("#{listCommandBean}")
	private ListCommandesBean listCommandesBean;

	@PostConstruct
	public void _init() {
		FactureController.LOGGER.debug("Chargement de la liste des factures.");
		this.factures = this.factureDao.readAll();
	}

	// Recuperer la commande via l'id commande
	public void readCommand(Integer idCommande) {
		Commande commande = commandeDao.read(idCommande);
		commandeBean.setId(commande.getId());
		commandeBean.setDateCommande(commande.getDateCommande());
		commandeBean.setDateEnvoi(commande.getDateEnvoi());
		commandeBean.setAckSent(commande.getAckSent());
		commandeBean.setAckReceived(commande.getAckReceived());
		Facture facture = factureDao.read(factureId);
		factureBean.setReference(facture.getReference());
		Etat etat = etatDao.read(commande.getEtatBean().getId());
		etatBean.setNom(etat.getNom());
	}

	public String save() {
		final Facture facture = new Facture();
		facture.setReference(this.factureBean.getReference());
		facture.setCommande(commandeDao.read(commandeId));
		facture.setMontant(this.factureBean.getMontant());
		facture.setDateFacture(this.factureBean.getDateFacture());

		try {
			if (this.factureId == null) {
				FactureController.LOGGER.debug("Création de la nouvelle facture", facture);
				this.factureDao.create(facture);
				FacesMessages.info("La facture a été créé avec succès.");
			} else {
				facture.setId(this.getFactureId());
				FactureController.LOGGER.debug("Mise à jour de la facture", facture);
				this.factureDao.update(facture);
				FacesMessages.info("La facture a été mis à jour avec succès.");
			}
		} catch (final CreateEntityException e) {
			FactureController.LOGGER.error("Erreur pendant la création de la nouvelle facture.", e);
			FacesMessages.error("Impossible de créer la facture.");
		} catch (final UpdateEntityException e) {
			FactureController.LOGGER.error("Erreur pendant la mise à jour de la facture d'id=" + this.factureId, e);
			FacesMessages.error("Impossible de mettre à jour cette facture.");
		}
		return "/views/dashboard";
	}

	public String delete(final Facture facture) {
		try {
			this.factureDao.delete(facture.getId());
			FacesMessages.info(
					"Facture d'id " + facture.getId() + " supprimé avec succès.");
		} catch (final DeleteEntityException e) {
			FactureController.LOGGER.error(
					"Erreur pendant la suppression de la facture", e);
			FacesMessages.error(
					"Impossible de supprimer la facture d'id " + facture.getId());
		}
		this.listCommandesBean.refresh();
		return "/views/facture/display";
	}

	public void selectCommande(final SelectEvent event) {
		this.commandeId = Integer.parseInt(event.getObject().toString());
	}

	/**
	 * @return the commandeId
	 */
	public int getCommandeId() {
		return commandeId;
	}

	/**
	 * @param commandeId
	 *            the commandeId to set
	 */
	public void setCommandeId(int commandeId) {
		this.commandeId = commandeId;
	}

	/**
	 * @return the listCommandesBean
	 */
	public ListCommandesBean getListCommandesBean() {
		return listCommandesBean;
	}

	/**
	 * @param listCommandesBean
	 *            the listCommandesBean to set
	 */
	public void setListCommandesBean(ListCommandesBean listCommandesBean) {
		this.listCommandesBean = listCommandesBean;
	}

	/**
	 * @return the etatBean
	 */
	public EtatBean getEtatBean() {
		return etatBean;
	}

	/**
	 * @param etatBean
	 *            the etatBean to set
	 */
	public void setEtatBean(EtatBean etatBean) {
		this.etatBean = etatBean;
	}

	/**
	 * @return the etatDao
	 */
	public EtatDao getEtatDao() {
		return etatDao;
	}

	/**
	 * @param etatDao
	 *            the etatDao to set
	 */
	public void setEtatDao(EtatDao etatDao) {
		this.etatDao = etatDao;
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

	/**
	 * @return the commandeDao
	 */
	public CommandeDao getCommandeDao() {
		return commandeDao;
	}

	/**
	 * @param commandeDao
	 *            the commandeDao to set
	 */
	public void setCommandeDao(CommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	public List<Facture> getFactures() {
		return this.factures;
	}

	/**
	 * @return the commandeBean
	 */
	public CommandeBean getCommandeBean() {
		return commandeBean;
	}

	/**
	 * @param commandeBean
	 *            the commandeBean to set
	 */
	public void setCommandeBean(CommandeBean commandeBean) {
		this.commandeBean = commandeBean;
	}
}
