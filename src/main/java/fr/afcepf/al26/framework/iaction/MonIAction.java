package fr.afcepf.al26.framework.iaction;

import fr.afcepf.al26.framework.action.MonActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface qui permet d'implementation d'une action.
 */
public interface MonIAction extends IAction{
    /**
     * méthode qui execute des parametres en entrée et
     * renvoi un résultat sous forme de {@link String}.
     *
     * @param paramActionForm  le {@link MonActionForm}.
     * @param paramRequest  la requete.
     * @param paramResponse la réponse.
     * @return une string.
     */
    String execute(MonActionForm paramActionForm,
                   HttpServletRequest paramRequest,
                   HttpServletResponse paramResponse);
}
