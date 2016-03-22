package fr.afcepf.al26.framework.factory;

import fr.afcepf.al26.framework.action.Action;

/**
 * Classe fabrique qui permet de generer les differentes actions
 * en fonction de leurs type.
 */
public class FabriqueAction {
    private FabriqueAction() {
    }

    public static Action create(String type) {
        Action action = null;
        try {
            action = (Action) Class.forName(type).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException paramE) {
            paramE.printStackTrace();
        }
        return action;
    }
}
