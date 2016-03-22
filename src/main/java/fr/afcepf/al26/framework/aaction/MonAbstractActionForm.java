package fr.afcepf.al26.framework.aaction;

/**
 * Classe abstraite qui permet de vérifier
 * la validation d'un formulaire.
 */
public abstract class MonAbstractActionForm {

    /**
     * méthode abstraite qui permet de lancer la vérification
     * des methodes via une config en xml.
     * @return vrai si le form est validé, faux
     * si le form est mal fait.
     */
    public abstract boolean validateForm();
}
