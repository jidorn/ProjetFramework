package fr.afcepf.al26.framework.factory;

import fr.afcepf.al26.framework.action.MonAction;

/**
 * Classe fabrique qui permet de generer les differentes actions
 * en fonction de leurs type.
 */
public class FabriqueAction {
    private FabriqueAction() {
    }

    public static MonAction create(String type) {
        MonAction action = null;
        try {
            action = (MonAction) Class.forName(type).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException paramE) {
            paramE.printStackTrace();
        }
        return action;
    }
}
