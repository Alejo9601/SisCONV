package Model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Alejandro Juarez
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static void configureSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");
            //apply configuration property settings to StandardServiceRegistryBuilder
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException ex) {
            // Log the exception.
            JOptionPane.showMessageDialog(
                    null,
                    "Initial SessionFactory creation failed. Make sure your Database is available " + ex,
                    "Warning", JOptionPane.WARNING_MESSAGE);
            //System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Returns session factory
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            configureSessionFactory();
        }
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
        if (sessionFactory != null) {
            sessionFactory.close();
        }//Closing session factory before configuring}
        modifyConnectConfiguration(dataBaseName, userName, password);
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
    private static void modifyConnectConfiguration(String dataBaseName, String userName, String password) throws FileNotFoundException, JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder(false);
        builder.setValidation(false);
        builder.setFeature("http://xml.org/sax/features/validation", false);
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        //Building new Document 
        Document documentJDOM = builder.build("hibernate.cfg.xml");
        XPathExpression<Element> xPathExpression = XPathFactory.instance().compile("/hibernate-configuration/session-factory/property", Filters.element());
        List<Element> elementList = xPathExpression.evaluate(documentJDOM);
        //Relative to position of element on list
        elementList.get(1).setText(dataBaseName);
        elementList.get(2).setText(userName);
        elementList.get(3).setText(password);
        //Preparing for overriding hibernate.cfg.xml
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(documentJDOM, new FileWriter("hibernate.cfg.xml"));
    }

}
