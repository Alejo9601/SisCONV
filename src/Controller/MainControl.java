package Controller;

import View.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Alejandro Juarez
 */
public class MainControl implements ActionListener {
    
    MainView pView;
    
    public MainControl() {
        this.pView = new MainView();
        this.pView.setController(this);
    }
    
    /**
     * This methos captures the action of the controlled view "MainView".
     * @param event 
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        RegistrationControl rc = new RegistrationControl();
        DetailControl dc = new DetailControl();
        switch(event.getActionCommand()) {
            
            case "REGISTER_AGREEMENT": 
                rc.showAgreementRegistrationView(true);
                break;
            case "AGREEMENTS_LIST":
                dc.ShowAgreementListView();
                break;
            case "PAYMENTS_LIST":
                break;
            
        }
        
    }

    /**
     * Will show the MainView.
     */
    void showPrincipalView() {
        pView.setVisible(true);
        pView.setLocationRelativeTo(null);
    }    
    
}
