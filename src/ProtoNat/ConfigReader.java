/**
 * RasberryPi ProtoNat
 * A very basic control to servo component, eyes animation and very basic AI approach. 
 * Written in Java using Pi4J. A Pi4J is a friendly object-oriented I/O API and implementation 
 * libraries for Java Programmers to access the full I/O capabilities of the Raspberry Pi platform.
 *
 * @author Christopher M. Natan
 */

package ProtoNat;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {
    private Map<String, String> config = new HashMap();

    public Map readConfig(String configFile) {
        Document xmlDoc = this.documentRead(configFile);
        Node node = xmlDoc.getElementsByTagName("config").item(0);
        NodeList nodeList = node.getChildNodes();

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node nodeItem = nodeList.item(index);
            if (nodeItem.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodeItem;
                String name = element.getNodeName();
                String value = element.getTextContent();
                this.config.put(name, value);
            }
        }
        return this.config;
    }

    public Document documentRead(String file) {
        Document xmlDoc = null;
        try {
            File xmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            xmlDoc = dBuilder.parse(xmlFile);
            xmlDoc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlDoc;
    }
}
