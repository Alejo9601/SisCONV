package Initializer;

import Controller.MainControl;
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
        if (SystemConfiguration.loadSystemProperties()) {
            if (SystemConfiguration.systemBegginingsCount() == 0) {
                MainControl mControl = new MainControl();
                mControl.showServerConfigurationView(null);
            } else {
                UserControl uControl = new UserControl();
                uControl.showLoginView();
            }
        }
    }

}
