package Controller;

import Model.UsersManager;
import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Alejandro Juarez
 */
public class MainControl implements ActionListener {

    MainView mainV;

    public MainControl() {
        this.mainV = new MainView();
        this.mainV.setController(this);
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
            case "CONCEPTS_LIST":
                dc = new DetailControl();
                dc.showConceptsListView();
                break;
            case "USERS_LIST":
                uc = new UserControl();
                uc.showListView();
                break;
            case "UPDATE_USER_PASSWORD":
                uc = new UserControl();
                uc.showPasswordChangeView(mainV);
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
