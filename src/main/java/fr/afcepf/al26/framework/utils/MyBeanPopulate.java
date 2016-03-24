package fr.afcepf.al26.framework.utils;

import fr.afcepf.al26.framework.action.MonActionForm;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe qui peuple le bean.
 */
public final class MyBeanPopulate {

    /**
     * le constructeur.
     */
    private MyBeanPopulate() {
    }

    /**
     * le log de la classe.
     */
    private static Logger log = Logger.getLogger(MyBeanPopulate.class);

    /**
     * methode principale pour generer le bean.
     *
     * @param monActionForm l'objet
     *                      {@link fr.afcepf.al26.framework.action.MonActionForm}.
     * @param params        le tableau des params.
     */
    public static void populateBean(Object monActionForm,
                                    Map<String, String[]> params) {
        log.info("la map : " + params.toString());
        Class classe = monActionForm.getClass();
        MonActionForm actionForm = (MonActionForm) monActionForm;
        log.info("la classe du populatebean : " + classe.toString());
        try {
            Map<String, Method> mapSetter = getMethods(classe, "set");
            checkObjetOuPrimitive(params, actionForm, mapSetter, classe);
            log.info("object : " + classe.getName());
        } catch (NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException paramE) {
            paramE.printStackTrace();
        }
    }

    private static void checkObjetOuPrimitive(Map<String, String[]> paramsEntry,
                                              MonActionForm paramActionForm,
                                              Map<String, Method>
                                                      paramMapSetter,
                                              Class classe)
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        for (Map.Entry<String, String[]> entry :
                paramsEntry.entrySet()) {
            String[] tableComplex = entry.getKey().split("\\.");
            log.info("la taille du tableau : " + tableComplex.length);
            if (tableComplex.length == 1
                    && paramMapSetter.containsKey(entry.getKey())) {
                log.info("je passe par le simple");
                Class type = paramMapSetter.get(entry.getKey())
                        .getParameterTypes()[0];
                setMethod(paramMapSetter.get(entry.getKey()), type,
                        entry.getValue()[0], paramActionForm);
            } else if (tableComplex.length > 1) {
                log.info("je passe par objet complexe");
                log.info("ancienne cle : " + entry.getKey().split("\\.")[0]);
                log.info("la nouvelle cle : " + entry.getKey().split("\\.")[1]);
                String newKey = entry.getKey().split("\\.")[0] +".";
                log.info("dans la methode du subMethod : "+newKey);

                /*
                Map<String, Method> subMethods =
                        getMethods(classe, newKey);
                checkObjetOuPrimitive(paramsEntry, paramActionForm,
                        subMethods, classe);
                */
            }
        }
    }

    /**
     * methode pour recuperer les methodes setter.
     *
     * @param paramClasse instance qui contient toutes les methodes.
     * @return une {@link Map} qui contient les methodes avec
     * en cle le nom de la methode et en valeur la methode.
     * @throws NoSuchMethodException     exception.
     * @throws IllegalAccessException    exception.
     * @throws InvocationTargetException exception.
     */
    private static Map<String,
            Method> getMethods(Class paramClasse, String regex)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        Map<String, Method> mapSetters = new HashMap<>();
        for (Method methodes :
                paramClasse.getMethods()) {
            if (methodes.getName().startsWith(regex)) {
                String nomMethode = methodes.getName().substring(3)
                        .toLowerCase();
                log.info("le nom de la methode :"+nomMethode);
                mapSetters.put(nomMethode, methodes);
            }
        }
        return mapSetters;
    }

    /**
     * methode qui set la methode dans l'instance.
     *
     * @param paramMethod     la methode à setter.
     * @param type            le type de la methode.
     * @param value           la valeur en string de la methode.
     * @param paramActionForm l'instance qui contient les methodes.
     * @throws NoSuchMethodException     exception.
     * @throws IllegalAccessException    exception.
     * @throws InvocationTargetException exception.
     */
    private static void setMethod(Method paramMethod,
                                  Class type,
                                  String value,
                                  MonActionForm paramActionForm)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        Object valueType = null;
        if (type.isPrimitive()) {
            switch (type.getName()) {
                case "int":
                    valueType = Integer.parseInt(value);
                    break;
                case "short":
                    valueType = Short.parseShort(value);
                    break;
                case "long":
                    valueType = Long.parseLong(value);
                    break;
                case "byte":
                    valueType = Byte.parseByte(value);
                    break;
                case "float":
                    valueType = Float.parseFloat(value);
                    break;
                case "double":
                    valueType = Double.parseDouble(value);
                    break;
                case "void":
                    valueType = Void.TYPE;
                    break;
                case "char":
                    valueType = value.charAt(0);
                    break;
                case "boolean":
                    valueType = Boolean.parseBoolean(value);
                    break;
            }
        } else if (type.getName()
                .equals(String.class.getName())) {
            valueType = value.toUpperCase();
        } else if (type.getName().equals(Date.class.getName())) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                valueType = sdf.parse(value);
            } catch (ParseException paramE) {
                paramE.printStackTrace();
            }
        }
        paramMethod.invoke(paramActionForm, valueType);
    }
/*
    public static String refactoMethodName(String nom) {
        Character tempUpper = nom.toUpperCase().charAt(0);
        String reste = nom.substring(1);
        return tempUpper + reste;
    }
    */
}
