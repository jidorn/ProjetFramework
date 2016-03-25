package fr.afcepf.al26.framework.factory;

import fr.afcepf.al26.framework.action.MonActionForm;
import org.apache.log4j.Logger;

/**
 * Classe fabrique qui permet de generer les differentes
 * {@link MonActionForm} en fonction de leurs type.
 */
public final class FabriqueActionForm {
    /**
     * le logger.
     */
    private static Logger log = Logger.getLogger(FabriqueActionForm.class);

    /**
     * le constructeur par defaut.
     */
    private FabriqueActionForm() {
    }

    /**
     * la methode de fabrication de l'objet.
     *
     * @param type le nom de l'instance de l'objet.
     * @return l'objet crée.
     */
    public static MonActionForm fabriqueActionForm(String type) {
        MonActionForm actionForm = null;
        try {
            actionForm = (MonActionForm) Class.forName(type).newInstance();
            log.info("l'action form : " + actionForm.toString());
        } catch (InstantiationException
                | IllegalAccessException
                | ClassNotFoundException paramE) {
            paramE.printStackTrace();
        }
        return actionForm;
    }

}
