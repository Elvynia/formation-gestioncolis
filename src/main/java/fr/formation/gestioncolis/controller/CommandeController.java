package fr.formation.gestioncolis.controller;

import fr.formation.gestioncolis.bean.CommandeBean;
import fr.formation.gestioncolis.dao.CommandeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class CommandeController implements Serializable {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CommandeController.class);

    @ManagedProperty("#{commandeBean}")
    private CommandeBean commandeBean;

    @ManagedProperty("#{commandeDao}")
    private CommandeDao commandeDao;



    public void setCommandeBean(CommandeBean commandeBean) {
        this.commandeBean = commandeBean;
    }

    public void setCommandeDao(CommandeDao commandeDao) {
        this.commandeDao = commandeDao;
    }
}
