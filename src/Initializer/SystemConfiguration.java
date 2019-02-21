package Initializer;

import Model.HibernateUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.jdom2.JDOMException;

/**
 *
 * @author Alejandro Juarez
 */
public class SystemConfiguration {

    private static final String SYSTEM_BEGGININGS_COUNT = "system_beggining_count";
    private static final String SERVER_URL = "server_url";
    private static final String SERVER_USERNAME = "server_username";
    private static final String SERVER_USERPASSWORD = "server_userpassword";

    public static boolean loadSystemProperties() {
        File propertiesFile = getPropertiesFile();
        try {
            Properties config = new Properties();
            InputStream configInput = new FileInputStream(propertiesFile.getAbsolutePath());
            config.load(configInput); //Loading properties
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error cargando configuración\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    /**
     *
     * @return
     */
    private static File getPropertiesFile() {
        //URL of directory in wich properties config file will be placed
        String userName = System.getProperty("user.name");
        String propertiesDirectory = "C:\\Users\\" + userName + "\\Documents\\SisCONV";
        //Creating directory, if exists wont throw any exception
        File directorio = new File(propertiesDirectory);
        directorio.mkdir();

        //Properties file verification
        File propertiesFile = new File(propertiesDirectory + "\\config.properties");
        if (!propertiesFile.exists()) {//If properties not exists
            try {
                propertiesFile.createNewFile(); //Creating new file
                //Writing properties at first system beggining
                FileWriter fw = new FileWriter(propertiesFile);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("#CUENTA DE LOS INICIOS DEL SISTEMA");
                bw.newLine();
                bw.write(SYSTEM_BEGGININGS_COUNT + "=0");
                bw.newLine();
                bw.newLine();

                bw.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return propertiesFile;
    }

    /**
     * Returns system begginings count
     *
     * @return
     */
    public static int systemBegginingsCount() {
        File propertiesFile = getPropertiesFile();
        int sbc = -1;
        try {
            Properties config = new Properties();
            InputStream configInput = new FileInputStream(propertiesFile.getAbsolutePath());
            config.load(configInput); //Loading properties
            sbc = Integer.parseInt(config.getProperty(SYSTEM_BEGGININGS_COUNT));
            return sbc;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error cargando configuración\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return sbc;
    }

    /**
     * Will set server new configuration parameters of the system
     *
     * @param serverURL
     * @param username
     * @param password
     * @return
     */
    public static boolean setServerConfiguration(String serverURL, String username, String password) {
        boolean flag = true;
        try {
            HibernateUtil.configureConnect(serverURL, username, password);
        } catch (JDOMException | IOException ex) {
            flag = false;
        }
        return flag;
    }

    /**
     * Will test server connection
     *
     * @return
     */
    public static boolean testServerConnection() {
        boolean flag = true;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
        } catch (HibernateException e) {
            flag = false;
        }
        return flag;
    }

    /**
     *
     */
    public static void incrementSystemBegginings() {
        File propertiesFile = getPropertiesFile();
        try {
            Properties config = new Properties();
            InputStream configInput = new FileInputStream(propertiesFile.getAbsolutePath());
            config.load(configInput); //Loading properties
            int count = systemBegginingsCount();
            count++;
            config.setProperty(SYSTEM_BEGGININGS_COUNT, Integer.toString(count));
            config.store(new FileWriter(propertiesFile.getAbsolutePath()), "INCREMENTO");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error cargando configuración\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
