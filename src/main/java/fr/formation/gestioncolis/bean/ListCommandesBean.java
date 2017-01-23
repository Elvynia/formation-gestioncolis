package fr.formation.gestioncolis.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.formation.gestioncolis.dao.CommandeDao;
import fr.formation.gestioncolis.entity.Commande;

@ManagedBean
@ViewScoped
public class ListCommandesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{commandeDao}")
	private CommandeDao commandeDao;

	private List<Commande> list;

	@PostConstruct
	public void _init() {
		this.refresh();
	}

	public void refresh() {
		this.list = this.commandeDao.readAll();
		// Nouvelle notation Java 7 : Lambda.
		// Collections.sort(this.list,
		// (o1, o2) -> Integer.compare(o1.getgetOrdre(), o2.getOrdre()));
		// Nouvelle notation Java 8.
		// Collections.sort(this.list, Comparator.comparingInt(Etat::getOrdre));
	}

	/**
	 * @return the commandeDao
	 */
	public CommandeDao getCommandeDao() {
		return commandeDao;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Commande> list) {
		this.list = list;
	}

	/**
	 * @param commandeDao
	 *            the commandeDao to set
	 */
	public void setCommandeDao(final CommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	/**
	 * @return the list
	 */
	public List<Commande> getList() {
		return this.list;
	}

}
