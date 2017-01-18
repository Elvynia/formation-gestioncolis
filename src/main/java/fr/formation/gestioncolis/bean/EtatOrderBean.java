package fr.formation.gestioncolis.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import fr.formation.gestioncolis.dao.EtatDao;
import fr.formation.gestioncolis.entity.Etat;

@ManagedBean
@ViewScoped
public class EtatOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{etatDao}")
	private EtatDao etatDao;

	private List<Etat> list;

	@PostConstruct
	public void _init() {
		this.refresh();
	}

	/**
	 * @return the list
	 */
	public List<Etat> getList() {
		return this.list;
	}

	public void refresh() {
		this.list = this.etatDao.readAll();
		// Nouvelle notation Java 7 : Lambda.
		Collections.sort(this.list,
				(o1, o2) -> Integer.compare(o1.getOrdre(), o2.getOrdre()));
		// Nouvelle notation Java 8.
		// Collections.sort(this.list, Comparator.comparingInt(Etat::getOrdre));
	}

	/**
	 * @param etatDao the etatDao to set
	 */
	public void setEtatDao(final EtatDao etatDao) {
		this.etatDao = etatDao;
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
			final Integer index = this.list.stream().map(Etat::getId)
					.collect(Collectors.toList()).indexOf(id);
			this.list.get(index).setOrdre(i);
		}
	}

}
