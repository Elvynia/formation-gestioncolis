package fr.formation.gestioncolis.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

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

	/**
	 * @return the list
	 */
	public List<Commande> getList() {
		return this.list;
	}

	public void refresh() {
		this.list = this.commandeDao.readAll();
		// Nouvelle notation Java 7 : Lambda.
//		Collections.sort(this.list,
//				(o1, o2) -> Integer.compare(o1.getgetOrdre(), o2.getOrdre()));
		// Nouvelle notation Java 8.
		// Collections.sort(this.list, Comparator.comparingInt(Etat::getOrdre));
	}

	/**
	 * @param commandeDao the commandeDao to set
	 */
	public void setCommandeDao(final CommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(final List<String> list) {
		for (int i = 0; i < list.size(); ++i) {
			final Integer id = Integer.parseInt(list.get(i));
			// Avant l'API Stream de Java 8 :
			// final Etat etatTmp = new Etat();
			// etatTmp.setId(id);
			// final Integer index = this.list.indexOf(etatTmp);
			// Avec l'API Stream :
			final Integer index = this.list.stream().map(Commande::getId)
					.collect(Collectors.toList()).indexOf(id);
			this.list.get(index);
		}
	}

}
