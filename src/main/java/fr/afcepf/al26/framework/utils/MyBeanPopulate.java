package fr.afcepf.al26.framework.utils;

import fr.afcepf.al26.framework.action.MonActionForm;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
            Map<String, Method> mapSetter = getSetter(classe);
            for (Map.Entry<String, String[]> entry :
                    params.entrySet()) {
                if (mapSetter.containsKey(entry.getKey())) {
                    Class type = mapSetter.get(entry.getKey())
                            .getParameterTypes()[0];
                    setMethod(mapSetter.get(entry.getKey()), type,
                            entry.getValue()[0], actionForm);
                }
            }
            log.info("object : " + classe.getName());
        } catch (NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException paramE) {
            paramE.printStackTrace();
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
            Method> getSetter(Class paramClasse)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        Map<String, Method> mapSetters = new HashMap<>();
        for (Method methodes :
                paramClasse.getMethods()) {
            if (methodes.getName().startsWith("set")) {
                String nomMethode = methodes.getName().substring(3)
                        .toLowerCase();
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
        log.info("le type : " + type);
        log.info("le type.getName : " + type.getName());
        log.info("le type name.getClass : " + type.getClass());
        log.info("le type est primitif ? : " + type.isPrimitive());
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
                    valueType = null;
                    break;
                case "boolean":
                    valueType = Boolean.parseBoolean(value);
                    break;
            }
        } else if (type.getName()
                .equals("java.lang.String")) {
            valueType = String.class.getName();
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
