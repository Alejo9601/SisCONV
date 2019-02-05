package Controller;

import View.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Alejandro Juarez
 */
public class UserControl implements ActionListener {

    LoginView lView;

    /**
     * Constructor of the class
     */
    public UserControl() {

        lView = new LoginView();
        lView.setController(this);

    }

    /**
     * Overrides the method actionPerformed of the ActionListener interface
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        switch (event.getActionCommand()) {

            case "LOGIN":

                MainControl pControl = new MainControl();
                pControl.showPrincipalView();

                lView.dispose();

                break;

            case "CANCEL":

                lView.dispose();
                
                break;

        }

    }

    /**
     * Will show the LoginView so it can be manipulated for the user.
     */
    public void showLoginView() {

        lView.setLocationRelativeTo(null);
        lView.setVisible(true);

    }

}
