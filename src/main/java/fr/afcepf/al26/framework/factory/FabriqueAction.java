package fr.afcepf.al26.framework.factory;

import fr.afcepf.al26.framework.action.MonAction;
import org.apache.log4j.Logger;

/**
 * Classe fabrique qui permet de generer les differentes actions
 * en fonction de leurs type.
 */
public class FabriqueAction {
    private static Logger log = Logger.getLogger(FabriqueAction.class);
    private FabriqueAction() {
    }

    public static MonAction create(String type) {
        MonAction action = null;
        try {
            action = (MonAction) Class.forName(type).newInstance();
            log.info("la classe action : " + action);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException paramE) {
            paramE.printStackTrace();
        }
        return action;
    }
}
