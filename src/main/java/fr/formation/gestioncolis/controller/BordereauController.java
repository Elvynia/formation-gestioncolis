package fr.formation.gestioncolis.controller;

import fr.formation.gestioncolis.bean.BordereauBean;
import fr.formation.gestioncolis.dao.BordereauDao;
import fr.formation.gestioncolis.entity.Bordereau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

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
    public void _init(){
        BordereauController.LOGGER.debug("Chargement de la liste des bordereaux.");
        this.bordereaux = bordereauDao.readAll();
    }

    public void detail(Integer id){

    }

    public List<Bordereau> getBordereaux() {
        return bordereaux;
    }

    public void setBordereauBean(BordereauBean bordereauBean) {
        this.bordereauBean = bordereauBean;
    }

    public void setBordereauDao(BordereauDao bordereauDao) {
        this.bordereauDao = bordereauDao;
    }

    public void setBordereaux(List<Bordereau> bordereaux) {
        this.bordereaux = bordereaux;
    }
}
