package Controller;

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
        RegistrationControl rc = new RegistrationControl();
        DetailControl dc = new DetailControl();
        UserControl uc = new UserControl();
        switch (event.getActionCommand()) {
            case "REGISTER_AGREEMENT":
                rc.showAgreementRegistrationView(true);
                break;
            case "AGREEMENTS_LIST":
                dc.ShowAgreementListView();
                break;
            case "PAYMENTS_LIST":
                break;
            case "CONCEPTS_LIST":
                dc.showConceptsListView();
                break;
            case "USER_REGISTRATION":
                uc.showRegistrationView(mainV);
                break;
        }
    }

    /**
     * Will show the MainView.
     */
    void showPrincipalView() {
        mainV.setVisible(true);
        mainV.setLocationRelativeTo(null);
    }

}
