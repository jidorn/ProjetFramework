package fr.afcepf.al26.framework.iaction;

/**
 * Interface qui permet de valider un formulaire.
 */
public interface MonIActionForm {

    /**
     * méthode qui permet de lancer la vérification
     * des methodes via une config en xml.
     *
     * @return vrai si le form est validé, faux
     * si le form est mal fait.
     */
    boolean validateForm();
}
