package fr.afcepf.al26.framework.servlet;

import com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultDocument;
import fr.afcepf.al26.framework.fallout.ActionClasse;
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
import java.util.Map;

/**
 * Classe {@link HttpServlet} qui fait office de controller.
 */
public class MaServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(MaServlet.class);
    Map<String, ActionClasse> actionsMap;

    @Override
    protected void doGet(HttpServletRequest paramRequest,
                         HttpServletResponse paramResponse)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest paramHttpServletRequest,
                          HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
        log.info("je passe par le doPost");
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
            actionsMap = hidrateActions(document, actionsMap);
            actionsMap = hidrateActionsForm(document,actionsMap);
            log.info("je passe apres hydrate");
        }
    }

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
        } catch (ParserConfigurationException | SAXException | IOException paramE) {
            paramE.printStackTrace();
        }
        return configFile;
    }

    public static Map<String, ActionClasse> hidrateActions(Document paramDocument,
                                                           Map<String, ActionClasse> paramActionsMap) {
        NodeList nodeList = paramDocument.getElementsByTagName("action");
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList nodeParent = nodeList.item(i).getChildNodes();
            String actionName = null;
            String formName = null;
            String urlPattern = null;
            for (int j = 0; j < nodeParent.getLength(); j++) {
                Node child = nodeParent.item(j);
                if (child.getNodeName().equals("action-name")) {
                    actionName = child.getTextContent();
                    log.info("le action-name"+child.getTextContent());
                }
                if (child.getNodeName().equals("url-pattern")) {
                    urlPattern = child.getTextContent();
                    log.info("le urlpattern"+child.getTextContent());
                }
                if (child.getNodeName().equals("form-name")) {
                    formName = child.getTextContent();
                    log.info("le form-name"+child.getTextContent());
                }
                ActionClasse monAction = new ActionClasse(formName, actionName, urlPattern);
                paramActionsMap.put(urlPattern, monAction);
            }
        }
        return paramActionsMap;
    }

    public static Map<String, ActionClasse> hidrateActionsForm(Document paramDocument,
                                                               Map<String, ActionClasse> paramActionsMap) {
        NodeList nodeList = paramDocument.getElementsByTagName("form");
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList nodeParent = nodeList.item(i).getChildNodes();
            String formClass = null;
            String formName = null;
            for (int j = 0; j < nodeParent.getLength(); j++) {
                Node child = nodeParent.item(j);
                if (child.getNodeName().equals("form-class")) {
                    formClass = child.getTextContent();
                    log.info("le form-class"+child.getTextContent());
                }
                if (child.getNodeName().equals("form-name")) {
                    formName = child.getTextContent();
                    log.info("le form-name"+child.getTextContent());
                }
            }
            for (Map.Entry<String, ActionClasse> temp :
                    paramActionsMap.entrySet()) {
                if (temp.getValue().getFormName().equals(formName)){
                    temp.getValue().setFormClass(formClass);
                }
            }
        }
        return paramActionsMap;
    }
}
