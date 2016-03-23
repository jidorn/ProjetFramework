package fr.afcepf.al26.framework.fallout;

/**
 * classe qui permet de faire un sylvain dans un ferhat.
 */
public class ActionClasse {
    private String formName;
    private String formClass;
    private String actionName;
    private String urlPattern;

    public ActionClasse() {
    }

    public ActionClasse(String paramFormName,
                        String paramActionName,
                        String paramUrlPattern) {
        formName = paramFormName;
        actionName = paramActionName;
        urlPattern = paramUrlPattern;
    }

    @Override
    public String toString() {
        return "ActionClasse{" +
                "formName='" + formName + '\'' +
                ", formClass='" + formClass + '\'' +
                ", actionName='" + actionName + '\'' +
                ", urlPattern='" + urlPattern + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object paramO) {
        if (this == paramO) return true;
        if (!(paramO instanceof ActionClasse)) return false;

        ActionClasse that = (ActionClasse) paramO;

        if (getFormName() != null ? !getFormName().equals(that.getFormName()) : that.getFormName() != null)
            return false;
        if (getFormClass() != null ? !getFormClass().equals(that.getFormClass()) : that.getFormClass() != null)
            return false;
        if (getActionName() != null ? !getActionName().equals(that.getActionName()) : that.getActionName() != null)
            return false;
        return getUrlPattern() != null ? getUrlPattern().equals(that.getUrlPattern()) : that.getUrlPattern() == null;

    }

    @Override
    public int hashCode() {
        int result = getFormName() != null ? getFormName().hashCode() : 0;
        result = 31 * result + (getFormClass() != null ? getFormClass().hashCode() : 0);
        result = 31 * result + (getActionName() != null ? getActionName().hashCode() : 0);
        result = 31 * result + (getUrlPattern() != null ? getUrlPattern().hashCode() : 0);
        return result;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String paramFormName) {
        formName = paramFormName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String paramActionName) {
        actionName = paramActionName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String paramUrlPattern) {
        urlPattern = paramUrlPattern;
    }

    public String getFormClass() {
        return formClass;
    }

    public void setFormClass(String paramFormClass) {
        formClass = paramFormClass;
    }
}
