package fr.afcepf.al26.framework.utils;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Classe qui peuple le bean.
 */
public class MyBeanPopulate {

    /**
     * le log de la classe.
     */
    private static Logger log = Logger.getLogger(MyBeanPopulate.class);

    /**
     * methode principale pour generer le bean.
     * @param monActionForm l'objet {@link fr.afcepf.al26.framework.action.MonActionForm}.
     * @param params le tableau des params.
     */
    public static void populateBean(Object monActionForm, Map<String, String[]> params) {
        log.info("la map : " + params.toString());
        Class classe = monActionForm.getClass();
        log.info("la classe du populatebean : " + classe.toString());
        try {
            for (Map.Entry<String, String[]> entry :
                    params.entrySet()) {
                setMethod(monActionForm, classe, entry);
            }
            log.info("object : " + classe.getName());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException paramE) {
            paramE.printStackTrace();
        }
    }

    private static void setMethod(Object monActionForm,
                                  Class paramClasse,
                                  Map.Entry<String,
                                          String[]> entry)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        String cle = entry.getKey();
        String nomMethodSet = "set" + refactoMethodName(cle);
        log.info(" le nom method " + nomMethodSet);
        Method setter = paramClasse.getMethod(nomMethodSet, new Class[]{String.class});
        setter.invoke(monActionForm, entry.getValue()[0]);
    }

    public static String refactoMethodName(String nom) {
        Character tempUpper = nom.toUpperCase().charAt(0);
        String reste = nom.substring(1);
        return tempUpper + reste;
    }
}
