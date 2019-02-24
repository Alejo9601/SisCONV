package Controller;

import Initializer.SystemConfiguration;
import Model.UsersManager;
import View.MainView;
import View.ServerConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Juarez
 */
public class MainControl implements ActionListener {

    MainView mainV;
    ServerConfiguration serverCV;

    public MainControl() {
        this.mainV = new MainView();
        this.mainV.setController(this);
    }

    /**
     *
     * @param parent
     */
    public void showServerConfigurationView(JFrame parent) {
        if (parent == null) {
            serverCV = new ServerConfiguration(null, true);
            serverCV.setController(this);
            serverCV.showAtfirstSystemBeggining();
        } else {

        }
    }

    /**
     * This methods captures the action of the controlled view "MainView".
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        RegistrationControl rc;
        DetailControl dc;
        UserControl uc;
        switch (event.getActionCommand()) {
            case "REGISTER_AGREEMENT":
                rc = new RegistrationControl();
                rc.showAgreementRegistrationView(true);
                break;
            case "AGREEMENTS_LIST":
                dc = new DetailControl();
                dc.ShowAgreementListView();
                break;
            case "ACTION_COMMITTEDS_LIST":
                uc = new UserControl();
                uc.showActionCommitedsView();
                break;
            case "CONCEPTS_LIST":
                dc = new DetailControl();
                dc.showConceptsListView();
                break;
            case "USERS_LIST":
                uc = new UserControl();
                uc.showUserListView();
                break;
            case "UPDATE_USER_PASSWORD":
                uc = new UserControl();
                uc.showPasswordChangeView(mainV);
                break;
            case "SAVE_SERVER_CONFIG":
                if (SystemConfiguration.setServerConfiguration(
                        serverCV.getTfUrlServer(),
                        serverCV.getTfUsername(),
                        serverCV.getTfPassword())) {
                    if (SystemConfiguration.testServerConnection()) {
                        JOptionPane.showMessageDialog(
                                serverCV,
                                "Informacion",
                                "La conexion esta establecida",
                                JOptionPane.INFORMATION_MESSAGE);
                        //If the parent isnt null, we are in application mainV
                        if (serverCV.getParent() != mainV) {
                            UserControl uControl = new UserControl();
                            uControl.showLoginView();
                        }
                        serverCV.dispose(); //Disposing server vie configuration view
                    } else {
                        JOptionPane.showMessageDialog(
                                serverCV,
                                "Advertencia",
                                "La conexion no pudo ser establecida",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }

                break;
        }
    }

    /**
     * Will show the MainView.
     */
    void showPrincipalView() {
        UsersManager um = UsersManager.getUsersManager();
        if (um.checkUserPermitions() == true) {
            mainV.enableAdminFunctions();
        }
        mainV.setVisible(true);
        mainV.setLocationRelativeTo(null);
    }

}
