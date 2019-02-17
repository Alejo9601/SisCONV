package Controller;

import Model.UsersManager;
import View.LoginView;
import View.UserRegistration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Juarez
 */
public class UserControl implements ActionListener {

    LoginView loginV;
    UserRegistration userRV;

    /**
     * Constructor of the class
     */
    public UserControl() {
        loginV = new LoginView();
        loginV.setController(this);
    }

    public void showRegistrationView(JFrame parent) {
        userRV = new UserRegistration(parent, true);
        userRV.setController(this);
        userRV.setLocationRelativeTo(null);
        userRV.setVisible(true);
    }

    /**
     * Will get user info from the view.
     *
     * @return
     */
    private EnumMap<UsersManager.user_param, String> getUserInfo() {
        EnumMap<UsersManager.user_param, String> userMap = new EnumMap<>(UsersManager.user_param.class);
        userMap.put(UsersManager.user_param.NAMES, userRV.getTfNames());
        userMap.put(UsersManager.user_param.LASTNAME, userRV.getTfLastname());
        userMap.put(UsersManager.user_param.NICKNAME, userRV.getTfNickName());
        userMap.put(UsersManager.user_param.PASSWORD, userRV.getTfPassword());
        if (userRV.getChkAdministrator() == true) {
            userMap.put(UsersManager.user_param.ADMINISTRATOR, "1");
        } else {
            userMap.put(UsersManager.user_param.ADMINISTRATOR, "0");
        }
        return userMap;
    }

    /**
     * Overrides the method actionPerformed of the ActionListener interface
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        UsersManager um = new UsersManager();
        switch (event.getActionCommand()) {
            case "LOGIN":
                if (um.validateSession(loginV.getTfPassword(), loginV.getTfNickName()) == true) {
                    MainControl pControl = new MainControl();
                    pControl.showPrincipalView();
                    loginV.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario o contraseña son incorrectos");
                }
                break;
            case "CANCEL":
                System.exit(0);
                break;
            case "SAVE_USER":
                if (um.newUser(getUserInfo()) == true) {
                    JOptionPane.showMessageDialog(userRV, "Se ha registrado con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    userRV.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario");
                }
                break;
        }

    }

    /**
     * Will show the LoginView so it can be manipulated for the user.
     */
    public void showLoginView() {
        loginV.setLocationRelativeTo(null);
        loginV.setVisible(true);
    }

}
