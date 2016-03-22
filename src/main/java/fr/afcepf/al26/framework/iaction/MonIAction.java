package fr.afcepf.al26.framework.iaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface qui permet d'implementation d'une action.
 */
public interface MonIAction {
    /**
     * méthode qui execute des parametres en entrée et
     * renvoi un résultat sous forme de {@link String}.
     * @param paramRequest la requete.
     * @param paramResponse la réponse.
     * @return une string.
     */
    String execute(HttpServletRequest paramRequest,
                   HttpServletResponse paramResponse);
}
