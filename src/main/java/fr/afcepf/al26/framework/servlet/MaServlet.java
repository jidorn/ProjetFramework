package fr.afcepf.al26.framework.servlet;

import com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultDocument;
import fr.afcepf.al26.framework.action.MonAction;
import fr.afcepf.al26.framework.action.MonActionForm;
import fr.afcepf.al26.framework.factory.FabriqueAction;
import fr.afcepf.al26.framework.factory.FabriqueActionForm;
import fr.afcepf.al26.framework.fallout.ActionClasse;
import fr.afcepf.al26.framework.utils.MyBeanPopulate;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe {@link HttpServlet} qui fait office de controller.
 */
public class MaServlet extends HttpServlet {
    /**
     * le logger de la classe.
     */
    private Logger log = Logger.getLogger(getClass());

    /**
     * la map qui contient les actions.
     */
    private Map<String, ActionClasse> actionsMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest paramRequest,
                         HttpServletResponse paramResponse)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest paramHttpServletRequest,
                          HttpServletResponse paramHttpServletResponse)
            throws ServletException, IOException {
        log.info("je passe par le doPost");
        String res = perform(paramHttpServletRequest,
                paramHttpServletResponse);

        paramHttpServletRequest.getRequestDispatcher(res)
                .forward(paramHttpServletRequest,
                        paramHttpServletResponse);
    }

    @Override
    public void init() throws ServletException {
        String context = Thread
                .currentThread()
                .getContextClassLoader()
                .getResource("../monframework-config.xml")
                .getPath();
        Document document;
        document = getXmlFile(context);
        log.info("le chemin : " + context);
        if (document != null) {
            log.info("je passe avant hydrate");
            hidrateActions(document);
            hidrateActionsForm(document);
            log.info("je passe apres hydrate");
        }
    }

    /**
     * le perform.
     *
     * @param paramRequest  la requete.
     * @param paramResponse la response.
     * @return une string.
     */
    private String perform(HttpServletRequest paramRequest,
                           HttpServletResponse paramResponse) {
        String forward = "";
        log.info("je suis dans le perform");
        String url = paramRequest.getServletPath().substring(1);
        log.info("le url : " + paramRequest.getServletPath().substring(1));
        log.info("la map :" + actionsMap.toString());
        if (actionsMap.containsKey(url)) {
            log.info("j'ai passé le premier stade");
            MonActionForm monActionForm = FabriqueActionForm
                    .fabriqueActionForm(actionsMap.get(url).getFormClass());
            if (monActionForm.validateForm()) {
                log.info("j'ai passé le deuxieme stade : "
                        + monActionForm.validateForm());
                MyBeanPopulate.populateBean(monActionForm,
                        paramRequest.getParameterMap());
                MonAction monAction = FabriqueAction
                        .create(actionsMap.get(url).getActionName());
                paramRequest.setAttribute(actionsMap.get(url)
                        .getFormName(), monActionForm);
                forward = monAction.execute(monActionForm,
                        paramRequest, paramResponse);
            }
        }
        return forward;
    }

    /**
     * methode pour recup le fichier xml.
     *
     * @param context le context.
     * @return le {@link Document}.
     */
    Document getXmlFile(String context) {

        Document configFile = new DefaultDocument();
        try {

            File file = new File(context);
            if (!file.exists()) {
                log.info("Mauvais chemin d'acces !");
            }
            DocumentBuilderFactory docBuilderFacto =
                    DocumentBuilderFactory
                            .newInstance();
            DocumentBuilder docBuilder = docBuilderFacto
                    .newDocumentBuilder();
            configFile = docBuilder.parse(file);
            configFile.getDocumentElement().normalize();
        } catch (ParserConfigurationException
                | SAXException | IOException paramE) {
            paramE.printStackTrace();
        }
        return configFile;
    }

    /**
     * methode qui recupere les actions.
     *
     * @param paramDocument le document config.xml.
     */
    public void hidrateActions(Document paramDocument) {
        NodeList nodeList = paramDocument.getElementsByTagName("action");
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList nodeParent = nodeList.item(i).getChildNodes();
            actionMapBuilder(nodeParent);
        }
    }

    /**
     * methode de creation d'une collection d'action.
     * @param paramNodeParent le noeud parent.
     */
    private void actionMapBuilder(NodeList paramNodeParent) {
        String actionName = null;
        String formName = null;
        String urlPattern = null;
        for (int j = 0; j < paramNodeParent.getLength(); j++) {
            Node child = paramNodeParent.item(j);
            if (child.getNodeName().equals("action-name")) {
                actionName = child.getTextContent();
            } else if (child.getNodeName().equals("url-pattern")) {
                urlPattern = child.getTextContent().substring(1);
            } else if (child.getNodeName().equals("form-name")) {
                formName = child.getTextContent();
            }
        }
        ActionClasse monAction =
                new ActionClasse(formName, actionName, urlPattern);
        actionsMap.put(urlPattern, monAction);
    }

    /**
     * methode qui recupere les actionsform.
     *
     * @param paramDocument le document config xml.
     */
    public void hidrateActionsForm(Document paramDocument) {
        NodeList nodeList = paramDocument.getElementsByTagName("form");
        for (int i = 0; i < nodeList.getLength(); i++) {
            actionFormBuilder(nodeList, i);
        }
    }

    /**
     * methode de creation d'une collection d'actionForm.
     * @param paramNodeList liste des noeuds.
     * @param paramI l'index d'incrementation.
     */
    private void actionFormBuilder(NodeList paramNodeList, int paramI) {
        NodeList nodeParent = paramNodeList.item(paramI).getChildNodes();
        String formClass = null;
        String formName = null;
        for (int j = 0; j < nodeParent.getLength(); j++) {
            Node child = nodeParent.item(j);
            if (child.getNodeName().equals("form-class")) {
                formClass = child.getTextContent();
            }
            if (child.getNodeName().equals("form-name")) {
                formName = child.getTextContent();
            }
        }
        for (Map.Entry<String, ActionClasse> temp
                : actionsMap.entrySet()) {
            if (temp.getValue().getFormName().equals(formName)) {
                temp.getValue().setFormClass(formClass);
            }
        }
    }
}
