/**
 * RasberryPi ProtoNat
 * A very basic control to servo component, eyes animation and very basic AI approach. 
 * Written in Java using Pi4J. A Pi4J is a friendly object-oriented I/O API and implementation 
 * libraries for Java Programmers to access the full I/O capabilities of the Raspberry Pi platform.
 *
 * @author Christopher M. Natan
 */

package ProtoNat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.*;

public class ServoAI extends ConfigReader {
    private Map<String, Action> action = new HashMap();
    private ArrayList actionList = new ArrayList();
    private Map config = new HashMap();
    private String fileGroups = "ai/servo/groups.xml";
    private String fileConfig = "ai/servo/config.xml";
    private Servo Servo = new Servo();

    public ServoAI() {
        this.config = super.readConfig(this.fileConfig);
        this.readGroups();
    }

    public class Action {
        private Map<String, String> execute = new HashMap();
        private ArrayList list = new ArrayList();

        protected void setValue(String name, String value) {
            this.execute.put(name, value);
        }

        public String getValue(String name) {
            return this.execute.get(name);
        }

        protected void setList(Integer index, String name) {
            this.list.add(index, name);
        }

        protected ArrayList getList() {
            return this.list;
        }
    }

    protected void readGroups() {
        Document documentGroups = this.documentRead(this.fileGroups);
        Node node= documentGroups.getChildNodes().item(0);
        NodeList nodeList = node.getChildNodes();
        Integer counter = 0;

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node nodeItem = nodeList.item(index);
            if (nodeItem.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodeItem;
                String name = element.getAttribute("name");

                Action execute = new Action();
                this.action.put(name,  this.Action(execute, element));
                this.actionList.add(counter, name);
                ++counter;
            }
        }
    }

    protected Action Action(Action execute, Element parentElement) {
        NodeList nodeList = parentElement.getChildNodes();
        Integer counter = 0;

        for (int index = 0; index < nodeList.getLength(); index++) {
            Node nodeItem = nodeList.item(index);
            if (nodeItem.getNodeType() == Node.ELEMENT_NODE) {
                String name = nodeItem.getNodeName();
                execute.setValue(name, this.getDefaultValue(name, nodeItem.getTextContent()));
                execute.setList(counter, name);
                ++counter;
            }
        }

        return execute;
    }

    public Map getConfig() {
        return this.config;
    }

    public Action getAction(String name) {
        return this.action.get(name);
    }

    public ArrayList getActionList() {
        return this.actionList;
    }

    public ArrayList getActionList(String name) {
        return this.action.get(name).getList();
    }

    public void setPins(String pins) {
        Servo.setPins(pins);
    }

    public void setAction(String fileAction) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Document documentGroups = this.documentRead(fileAction);
        NodeList nodeList =  documentGroups.getChildNodes().item(0).getChildNodes();
        this.readElement(nodeList, SetAction.class);
    }

    private class SetAction {
        public void execute(Element element) {
            String name = element.getNodeName();
            print(name);
            if(name.equalsIgnoreCase("action")) {
                if (element.hasChildNodes()) {

                }
            }
        }
    }

    protected void readElement(NodeList nodeList, Class cls) {
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node nodeItem = nodeList.item(index);
            if (nodeItem.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodeItem;
                try {
                    Class obj = Class.forName(cls.getName());

                    Method method = cls.getDeclaredMethod("execute", Element.class);
                    method.invoke(obj.newInstance(), element);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String getDefaultValue(String name, String value) {
        if(value.isEmpty()) {
            return this.config.get(name).toString();
        }
        return value;
    }

    private static void print(Object obj) {
        System.out.println(obj);
    }
    private static void print(String str) {
        System.out.println(str);
    }
}
