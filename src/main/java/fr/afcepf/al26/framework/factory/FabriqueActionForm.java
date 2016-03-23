package fr.afcepf.al26.framework.factory;

import fr.afcepf.al26.framework.action.MonActionForm;

/**
 * Classe fabrique qui permet de generer les differentes
 * {@link MonActionForm} en fonction de leurs type.
 */
public class FabriqueActionForm {
    private FabriqueActionForm(){}
    public static MonActionForm fabriqueActionForm (String type){
        MonActionForm actionForm = null;
        try {
            actionForm = (MonActionForm) Class.forName(type).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException paramE) {
            paramE.printStackTrace();
        }
        return actionForm;
    }
}
