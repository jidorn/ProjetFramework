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
