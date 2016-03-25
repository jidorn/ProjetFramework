package fr.afcepf.al26.framework.factory;

import fr.afcepf.al26.framework.action.MonAction;
import org.apache.log4j.Logger;

/**
 * Classe fabrique qui permet de generer les differentes actions
 * en fonction de leurs type.
 */
public final class FabriqueAction {

    /**
     * le logger de la classe.
     */
    private static Logger log = Logger.getLogger(FabriqueAction.class);

    /**
     * le constructeur par defaut.
     */
    private FabriqueAction() {
    }

    /**
     * la methode de creation du la classe.
     *
     * @param type le nom de l'instance.
     * @return l'objet action.
     */
    public static MonAction create(String type) {
        MonAction action = null;
        try {
            action = (MonAction) Class.forName(type).newInstance();
            log.info("la classe action : " + action);
        } catch (InstantiationException
                | IllegalAccessException
                | ClassNotFoundException paramE) {
            paramE.printStackTrace();
        }
        return action;
    }
}
