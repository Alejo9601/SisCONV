package Initializer;

import Controller.UserControl;

/**
 *
 * @author Alejandro Juarez
 */
public class SisCONV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        //If system loads properties
//        if (SystemConfiguration.loadSystemProperties()) {
//            //If the count is equals to 0
//            if (SystemConfiguration.systemBegginingsCount() == 0) {
//                MainControl mControl = new MainControl();
//                mControl.showServerConfigurationView(null); //We configure server
//            } else if (!SystemConfiguration.testServerConnection()) {
//                //Server its not configured correctly
//                if (JOptionPane.showConfirmDialog(
//                        null,
//                        "La base de datos no esta configurada correctamente, ¿Desea configurarla? ",
//                        "Informacion",
//                        JOptionPane.YES_NO_OPTION) == 0) {
//                    //If yes 
//                    MainControl mc = new MainControl();
//                    mc.showServerConfigurationView(null);
//                } else {
//                    System.exit(0);
//                }
//            } else { //IF theres is an availabe database
        UserControl uControl = new UserControl();
        uControl.showLoginView();
//            }
//        }
    }

}
