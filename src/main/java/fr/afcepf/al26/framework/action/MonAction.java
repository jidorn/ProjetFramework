package fr.afcepf.al26.framework.action;

import fr.afcepf.al26.framework.iaction.MonIAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * classe qui permet d'executer une action a
 * partir d'une servlet.
 */
public class MonAction implements MonIAction {
    /**
     * méthode qui execute des parametres en entrée et
     * renvoi un résultat sous forme de {@link String}.
     *
     * @param paramActionForm le {@link MonActionForm}.
     * @param paramRequest    la requete.
     * @param paramResponse   la réponse.
     * @return une string.
     */
    @Override
    public String execute(MonActionForm paramActionForm,
                          HttpServletRequest paramRequest,
                          HttpServletResponse paramResponse) {
        return "";
    }
}
