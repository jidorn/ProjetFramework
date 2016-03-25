package fr.afcepf.al26.framework.fallout;

/**
 * classe qui permet de faire un sylvain dans un ferhat.
 */
public class ActionClasse {

    /**
     * le formName.
     */
    private String formName;

    /**
     * le formClass.
     */
    private String formClass;

    /**
     * l'actionName.
     */
    private String actionName;

    /**
     * l'url pattern.
     */
    private String urlPattern;

    /**
     * le constructeur par defaut.
     */
    public ActionClasse() {
    }

    /**
     * le constructeur contenant tout les params.
     *
     * @param paramFormName   le forName.
     * @param paramActionName l'actionName.
     * @param paramUrlPattern urlPattern.
     */
    public ActionClasse(String paramFormName,
                        String paramActionName,
                        String paramUrlPattern) {
        formName = paramFormName;
        actionName = paramActionName;
        urlPattern = paramUrlPattern;
    }

    /**
     * la methode généré toString.
     *
     * @return la string de la classe.
     */
    @Override
    public String toString() {
        return "ActionClasse{"
                + "formName='" + formName + '\''
                + ", formClass='" + formClass + '\''
                + ", actionName='" + actionName + '\''
                + ", urlPattern='" + urlPattern + '\''
                + '}';
    }

    /**
     * la methode généré equals
     * pour que les petits lutins se
     * transforme en gros trolls.
     *
     * @param paramO le petit lutin.
     * @return un gros boolean de troll.
     */
    @Override
    public boolean equals(Object paramO) {
        if (this == paramO) return true;
        if (!(paramO instanceof ActionClasse)) return false;

        ActionClasse that = (ActionClasse) paramO;

        if (getFormName() != null ? !getFormName()
                .equals(that.getFormName()) : that.getFormName() != null)
            return false;
        if (getFormClass() != null ? !getFormClass()
                .equals(that.getFormClass()) : that.getFormClass() != null)
            return false;
        if (getActionName() != null ? !getActionName()
                .equals(that.getActionName()) : that.getActionName() != null)
            return false;
        return getUrlPattern() != null ? getUrlPattern()
                .equals(that.getUrlPattern()) : that.getUrlPattern() == null;

    }

    /**
     * le hashcode.
     *
     * @return le hashCode.
     */
    @Override
    public int hashCode() {
        int result = getFormName() != null ? getFormName().hashCode() : 0;
        result = 31 * result + (getFormClass() != null
                ? getFormClass().hashCode() : 0);
        result = 31 * result + (getActionName() != null
                ? getActionName().hashCode() : 0);
        result = 31 * result + (getUrlPattern() != null
                ? getUrlPattern().hashCode() : 0);
        return result;
    }

    /**
     * le getter.
     *
     * @return le getter.
     */
    public String getFormName() {
        return formName;
    }

    /**
     * le setter.
     *
     * @param paramFormName le setter.
     */
    public void setFormName(String paramFormName) {
        formName = paramFormName;
    }

    /**
     * le getter.
     *
     * @return le getter.
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * le setter.
     *
     * @param paramActionName le setter.
     */
    public void setActionName(String paramActionName) {
        actionName = paramActionName;
    }

    /**
     * le getter.
     *
     * @return le getter.
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     * le setter.
     *
     * @param paramUrlPattern le setter.
     */
    public void setUrlPattern(String paramUrlPattern) {
        urlPattern = paramUrlPattern;
    }

    /**
     * le getter.
     *
     * @return le getter.
     */
    public String getFormClass() {
        return formClass;
    }

    /**
     * le setter.
     *
     * @param paramFormClass le setter.
     */
    public void setFormClass(String paramFormClass) {
        formClass = paramFormClass;
    }
}
