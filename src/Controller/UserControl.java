package Controller;

import Initializer.SystemConfiguration;
import Model.UsersManager;
import View.LoginView;
import View.ActionCommittedsView;
import View.PasswordChange;
import View.UserRegistration;
import View.UsersList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro Juarez
 */
public class UserControl implements ActionListener, KeyListener {

    LoginView loginV;
    UsersList usersLV;
    UserRegistration userRV;
    PasswordChange passCV;
    ActionCommittedsView actionCV;
    UsersManager um = UsersManager.getUsersManager();

    /**
     * Constructor of the class
     */
    public UserControl() {
        actionCV = new ActionCommittedsView();
        loginV = new LoginView();
        usersLV = new UsersList();
        loginV.setController(this);
        usersLV.setController(this);
        actionCV.setController(this);
    }

    /**
     *
     */
    public void fillUsersListView() {
        List<EnumMap<UsersManager.user_param, String>> usersEL = um.consultAllUsers();
        //Table model creation
        DefaultTableModel tableModel = new DefaultTableModel(
                null, new String[]{
                    "NRO.",
                    "NOMBRE",
                    "APELLIDO",
                    "NICK",
                    "ADMINISTRADOR"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on landPropEL
        for (EnumMap<UsersManager.user_param, String> userMap : usersEL) {
            Object nuevo[] = new Object[]{
                userMap.get(UsersManager.user_param.ID_USER),
                userMap.get(UsersManager.user_param.NAMES),
                userMap.get(UsersManager.user_param.LASTNAME),
                userMap.get(UsersManager.user_param.NICKNAME),
                userMap.get(UsersManager.user_param.ADMINISTRATOR)};
            tableModel.addRow(nuevo);
        }
        usersLV.setTableModel(tableModel); //Setting model to the view.
    }

    /**
     *
     */
    public void fillLogView() {
        List<EnumMap<UsersManager.actionCommitted, String>> actionCommittedsEL = um.consultAllActionsCommitted();
        //Table model creation
        DefaultTableModel tableModel = new DefaultTableModel(
                null, new String[]{
                    "ID MOV.",
                    "FECHA",
                    "MOVIMIENTO",
                    "USUARIO",
                    "DESCRIPCION"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        //For each EnumMap on landPropEL
        for (EnumMap<UsersManager.actionCommitted, String> acMap : actionCommittedsEL) {
            Object nuevo[] = new Object[]{
                acMap.get(UsersManager.actionCommitted.ID_MOVEMENT),
                acMap.get(UsersManager.actionCommitted.DATE),
                acMap.get(UsersManager.actionCommitted.MOVEMENT),
                acMap.get(UsersManager.actionCommitted.USER_NAME),
                acMap.get(UsersManager.actionCommitted.DESCRIPTION)};
            tableModel.addRow(nuevo);
        }
        actionCV.setTableModel(tableModel); //Setting model to the view.
    }

    /**
     *
     */
    public void showActionCommitedsView() {
        fillLogView();
        actionCV.setLocationRelativeTo(null);
        actionCV.setVisible(true);
    }

    /**
     *
     */
    public void showUserListView() {
        fillUsersListView();
        usersLV.setLocationRelativeTo(null);
        usersLV.setVisible(true);
    }

    /**
     *
     * @param parent
     */
    public void showPasswordChangeView(JFrame parent) {
        passCV = new PasswordChange(parent, true);
        passCV.setController(this);
        passCV.setLocationRelativeTo(null);
        passCV.setVisible(true);
    }

    /**
     *
     * @param parent
     */
    public void showRegistrationView(JFrame parent) {
        userRV = new UserRegistration(parent, true);
        userRV.setController(this);
        userRV.setLocationRelativeTo(null);
        userRV.setVisible(true);
    }

    /**
     * Will get userMap info from the view.
     *
     * @return
     */
    private EnumMap<UsersManager.user_param, String> getUserInfo() {
        EnumMap<UsersManager.user_param, String> userMap = new EnumMap<>(UsersManager.user_param.class);
        userMap.put(
                UsersManager.user_param.NAMES, userRV.getTfNames());
        userMap.put(
                UsersManager.user_param.LASTNAME, userRV.getTfLastname());
        userMap.put(
                UsersManager.user_param.NICKNAME, userRV.getTfNickName());
        userMap.put(
                UsersManager.user_param.PASSWORD, userRV.getTfPassword());
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
        switch (event.getActionCommand()) {
            case "LOGIN":
                if (um.validateSession(loginV.getTfPassword(), loginV.getTfNickName())) {
                    SystemConfiguration.incrementSystemBegginings(); //We increment in 1
                    MainControl pControl = new MainControl();
                    pControl.showPrincipalView();
                    loginV.dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            userRV,
                            "La contraseña o usuario no son correctos",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "CANCEL":
                System.exit(0);
                break;
            case "SAVE_USER":
                if (um.newUser(getUserInfo())) {
                    JOptionPane.showMessageDialog(
                            userRV,
                            "Se ha registrado con exito",
                            "Informacion",
                            JOptionPane.INFORMATION_MESSAGE);
                    userRV.dispose();
                    fillUsersListView();
                } else {
                    JOptionPane.showMessageDialog(
                            userRV,
                            "No se pudo registrar el usuario",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "DELETE_USER":
                if (JOptionPane.showConfirmDialog(
                        usersLV,
                        "¿Esta seguro que desea eliminar el usuario?, "
                        + "los cambios surtiran efectos la proxima vez que este intente iniciar sesion",
                        "Advertencia",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    if (um.deleteUser(Integer.parseInt(usersLV.getSelectedUserID()))) {
                        JOptionPane.showMessageDialog(
                                userRV,
                                "Se ha eliminado con exito",
                                "Informacion",
                                JOptionPane.INFORMATION_MESSAGE);
                        fillUsersListView();
                    } else {
                        JOptionPane.showMessageDialog(
                                userRV,
                                "No se ha podido eliminar el usuario",
                                "Avertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "USER_REGISTRATION":
                showRegistrationView(loginV);
                break;
            case "UPDATE_USER_PASSWORD":
                if (passCV.verifyInformation()) {//if fields are completed
                    if ((um.isLoggedUserPassword(passCV.getTfActualPassword()))) {//If actual pass is valid
                        if ((Arrays.equals(passCV.getTfNewPassword1(), passCV.getTfNewPassword2()))) {//If both new password are equals
                            if (um.modifyUserPassword(passCV.getTfNewPassword1())) {//We call updateUserPassword
                                JOptionPane.showMessageDialog(
                                        userRV,
                                        "Se ha registrado con exito",
                                        "Informacion",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(
                                        userRV,
                                        "No se pudo realizar la operacion",
                                        "Adevertencia",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    userRV,
                                    "Las contraseñas nuevas no coinciden",
                                    "Advertencia",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                userRV,
                                "La contraseña actual no es valida",
                                "Advertencia",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
        }

    }

    /**
     * Will show the LoginView so it can be manipulated for the userMap.
     */
    public void showLoginView() {
        loginV.setLocationRelativeTo(null);
        loginV.setVisible(true);
        SystemConfiguration.testServerConnection();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char c = ke.getKeyChar();
        if (Character.isLetter(c) || Character.isDigit(c) || c == ' ' || c == '-') {
        } else {
            ke.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (actionCV != null) {
            if (ke.getSource() == actionCV.getTfSearch()) {
                fillLogView();
                actionCV.filterMovements();
            }
        }
    }

}
