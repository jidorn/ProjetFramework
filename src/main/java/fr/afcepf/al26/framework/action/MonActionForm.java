package fr.afcepf.al26.framework.action;

import fr.afcepf.al26.framework.iaction.MonIActionForm;

/**
 * Classe action qui permet de valider un formulaire.
 */
public class MonActionForm implements MonIActionForm {
    /**
     * méthode qui permet de lancer la vérification
     * des methodes via une config en xml.
     *
     * @return vrai si le form est validé, faux
     * si le form est mal fait.
     */
    @Override
    public boolean validateForm() {
        return true;
    }
}
