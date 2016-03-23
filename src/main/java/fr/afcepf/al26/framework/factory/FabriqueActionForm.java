package fr.afcepf.al26.framework.factory;

import fr.afcepf.al26.framework.action.MonActionForm;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Classe fabrique qui permet de generer les differentes
 * {@link MonActionForm} en fonction de leurs type.
 */
public class FabriqueActionForm {
    private static Logger log = Logger.getLogger(FabriqueActionForm.class);
    private FabriqueActionForm(){}
    public static MonActionForm fabriqueActionForm (String type){
        MonActionForm actionForm = null;
        try {
            actionForm = (MonActionForm) Class.forName(type).newInstance();
            log.info("l'action form : "+actionForm.toString());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException paramE) {
            paramE.printStackTrace();
        }
        return actionForm;
    }

}
