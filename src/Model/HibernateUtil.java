package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.DOMOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.jdom2.Document;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Alejandro Juarez
 */
public class HibernateUtil {

    private static URL xmlFileURL;
    private static SessionFactory sessionFactory;

    static {
        xmlFileURL = HibernateUtil.class.getClassLoader().getResource("hibernate.cfg.xml");
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure(xmlFileURL).buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception.
            JOptionPane.showMessageDialog(
                    null,
                    "Initial SessionFactory creation failed. Make sure your Database is available " + ex,
                    "Warning", JOptionPane.WARNING_MESSAGE);
//            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 
     * @param dataBaseName
     * @param userName
     * @param password
     * @throws FileNotFoundException
     * @throws JDOMException
     * @throws IOException 
     */
    public static void configureConnect(String dataBaseName, String userName, String password) throws FileNotFoundException, JDOMException, IOException {
        sessionFactory.close();
        sessionFactory = new Configuration().configure(modifyConnectConfiguration(dataBaseName, userName, password)).buildSessionFactory();
    }

    /**
     * 
     * @param dataBaseName
     * @param userName
     * @param password
     * @return
     * @throws FileNotFoundException
     * @throws JDOMException
     * @throws IOException 
     */
    private static org.w3c.dom.Document modifyConnectConfiguration(String dataBaseName, String userName, String password) throws FileNotFoundException, JDOMException, IOException {
        Document documentJDOM = new SAXBuilder().build(xmlFileURL);
        XPathExpression<Element> xPathExpression = XPathFactory.instance().compile("/hibernate-configuration/session-factory/property", Filters.element());
        List<Element> elementList = xPathExpression.evaluate(documentJDOM);
        //Esto es relativo a en que posición aparecen las lineas en el hibernate.cfg.xml
        elementList.get(2).setText(dataBaseName);
        elementList.get(3).setText(userName);
        elementList.get(4).setText(password);
        DOMOutputter domOutputter = new DOMOutputter();
        return domOutputter.output(documentJDOM);
    }
}
