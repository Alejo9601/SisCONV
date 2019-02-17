package Initializer;

import Controller.UserControl;



/**
 *
 * @author Alejandro Juarez
 */
public class FinalProyect {
//
//    private final static Logger log = Logger.getLogger(FinalProyect.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        PropertyConfigurator.configure("log4j.properties");
        
//        log.info("Inicio programa");
        UserControl uControl = new UserControl();
        uControl.showLoginView();

    }

}
