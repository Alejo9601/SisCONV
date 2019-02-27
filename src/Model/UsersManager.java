package Model;

import Model.DTO.ActionCommitted;
import Model.DTO.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alejandro Juarez
 */
public class UsersManager {

    private User loggedUser;//User logged in DB.
    private static UsersManager uniqueInstance; //Singleton pattern

    public static enum user_param {
        NAMES, LASTNAME, NICKNAME, PASSWORD, ADMINISTRATOR, ID_USER
    };

    public static enum actionCommitted {
        ID_MOVEMENT, DATE, MOVEMENT, USER_NAME, DESCRIPTION
    }

    public static enum user_actions {
        AGREEMENT_REGISTRATION("REGISTRÓ CONVENIO"),
        PAYMENT_REGISTRATION("REGISTRÓ PAGO"),
        LEAVE_AGREEMENT_WITHOUT_EFFECT("DEJÓ SIN EFECTO"),
        DELETE_PAYMENT("ELIMINÓ UN PAGO"),
        MODIFY_AGREEMENT("MODIFICÓ CONVENIO");

        private final String value;

        user_actions(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Private constructor
     */
    private UsersManager() {

    }

    /**
     *
     * @return
     */
    public static UsersManager getUsersManager() {
        if (uniqueInstance == null) {
            uniqueInstance = new UsersManager();
        }
        return uniqueInstance;
    }

    /**
     * Returns the logged user of this unique instance
     *
     * @return
     */
    public EnumMap<user_param, String> getLoggedUser() {
        return userOnEnumMap(loggedUser);
    }

    /**
     * Returns the boolean if logged user is administrator
     *
     * @return
     */
    public boolean checkUserPermitions() {
        return loggedUser.getAdministrator() == 1;
    }

    /**
     *
     * @param password
     * @return
     */
    public boolean isLoggedUserPassword(char[] password) {
        char[] passwordDB = loggedUser.getPassword().toCharArray();
        if (password.length == passwordDB.length) { //If the passwords char arrays are the same lenght
            for (int i = 0; i < password.length; i++) {
                if (passwordDB[i] != password[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    private boolean isSuperUser(User user) {
        return user.getNickName().equals("ADMINBD2896") && user.getPassword().equals("adminroot28");
    }

    /**
     *
     * @param userMap
     * @return
     */
    public boolean newUser(EnumMap<user_param, String> userMap) {
        //Unpacking all the data.
        User user = unpackUserMap(userMap);
        //Opening Hibernate session.
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion dando de alta un nuevo usuario" + e);
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     * Will consultUser an user from DB.
     *
     * @param nickname
     * @return
     */
    private User consultUser(String nickname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM user WHERE user.nickname = '" + nickname + "'");
            consult.addEntity(User.class);
            if (consult.uniqueResult() != null) {
                user = (User) consult.uniqueResult();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando al usuario" + e);
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Gets all the agreements from DB.
     *
     * @return
     */
    public List<EnumMap<user_param, String>> consultAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> agreementL = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM user");
            consult.addEntity(User.class);
            agreementL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando a los usuarios" + e);
        } finally {
            session.close();
        }
        return usersOnEnumList(agreementL);
    }

    public boolean modifyUserPassword(char[] password) {
        String stringPass = String.valueOf(password);
//        for(int i = 0; i < password.length; i++) {
//            stringPass.concat(String.password[i]);
//        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean flag = true;
        try {
            transaction = session.beginTransaction();
            SQLQuery consult = session.createSQLQuery("UPDATE user SET password = '"
                    + stringPass
                    + "' WHERE user.nickName = '" + loggedUser.getNickName() + "'");
            consult.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            flag = false;
            JOptionPane.showMessageDialog(
                    null, "Excepcion al tratar de modificar la contraseña del usuario",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     *
     * @param userID
     * @return
     */
    public boolean deleteUser(int userID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            SQLQuery consult = session.createSQLQuery(
                    "DELETE FROM user WHERE user.id_user = " + userID);
            consult.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion tratando de eliminar el usuario" + e);
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     * Extracts all the data of an user from EnumMap.
     *
     * @param userMap
     * @return
     */
    private User unpackUserMap(EnumMap<user_param, String> userMap) {
        User user = new User(userMap.get(user_param.NAMES),
                userMap.get(user_param.LASTNAME),
                userMap.get(user_param.NICKNAME),
                userMap.get(user_param.PASSWORD),
                Byte.valueOf(userMap.get(user_param.ADMINISTRATOR)));
        user.setActiveSession((byte) 0);
        return user;
    }

    /**
     * Will construct a list of enum maps, each one of them with information of
     * a user.
     *
     * @return
     */
    private List<EnumMap<user_param, String>> usersOnEnumList(List<User> usersL) {
        if (usersL != null) {
            List<EnumMap<user_param, String>> usersEL = new ArrayList<>();
            for (User u : usersL) {
                if (!isSuperUser(u)) {
                    usersEL.add(userOnEnumMap(u));
                }
            }
            return usersEL;
        }
        return null;
    }

    /**
     * Will construct an EnumMap with the information of the agreement.
     *
     * @param user
     * @return
     */
    public EnumMap<user_param, String> userOnEnumMap(User user) {
        if (user != null) {
            EnumMap<user_param, String> userMap = new EnumMap<>(user_param.class);
            userMap.put(user_param.ID_USER, Integer.toString(user.getIdUser()));
            userMap.put(user_param.NAMES, user.getNames());
            userMap.put(user_param.LASTNAME, user.getLastname());
            userMap.put(user_param.NICKNAME, user.getNickName());
            userMap.put(user_param.PASSWORD, user.getPassword());
            if (user.getAdministrator() == 1) {
                userMap.put(user_param.ADMINISTRATOR, "SI");
            } else {
                userMap.put(user_param.ADMINISTRATOR, "NO");
            }
            return userMap;
        }
        return null;
    }

//    /**
//     *
//     */
//    private boolean markSessionAsActive() {
//        //Opening Hibernate session.
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = null;
//        boolean flag = true; //Flag that indicates if the operation finished succesfully.
//        try {
//            transaction = session.beginTransaction();
//            SQLQuery consultUser = session.createSQLQuery(
//                    "UPDATE user SET activeSession = " + (byte) 1 + " WHERE user.idUser = " + loggedUser.getIdUser());
//            consultUser.executeUpdate();
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
//                transaction.rollback();
//            }
//            JOptionPane.showMessageDialog(null, "Excepcion marcando al usuario como activo" + e);
//            flag = false;
//        } finally {
//            session.close();
//        }
//        return flag;
//    }
    public void registerUserAction(user_actions action, String description) {
        //Opening Hibernate session.
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        ActionCommitted actionComm = new ActionCommitted(
                action.getValue(),
                new Date(),
                loggedUser.getNames()
                + " " + loggedUser.getLastname()
                + " : " + description);
        try {
            transaction = session.beginTransaction();
            session.save(actionComm);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion registrando la accion del usuario" + e);
        } finally {
            session.close();
        }
    }

    /**
     * Gets all the agreements from DB.
     *
     * @return
     */
    public List<EnumMap<actionCommitted, String>> consultAllActionsCommitted() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ActionCommitted> actionCommittedsL = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM action_committed");
            consult.addEntity(ActionCommitted.class);
            actionCommittedsL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el log de acciones" + e);
        } finally {
            session.close();
        }
        return actionsOnEnumList(actionCommittedsL);
    }

    /**
     *
     * @param actionsL
     * @return
     */
    private List<EnumMap<actionCommitted, String>> actionsOnEnumList(List<ActionCommitted> actionsL) {
        if (actionsL != null) {
            List<EnumMap<actionCommitted, String>> actionCommitedsEL = new ArrayList<>();
            for (ActionCommitted a : actionsL) {
                actionCommitedsEL.add(actionCommittedOnEnumMap(a));
            }
            return actionCommitedsEL;
        }
        return null;
    }

    /**
     *
     * @param actionC
     * @return
     */
    private EnumMap<actionCommitted, String> actionCommittedOnEnumMap(ActionCommitted actionC) {
        if (actionC != null) {
            EnumMap<actionCommitted, String> actionCMAP = new EnumMap<>(actionCommitted.class);
            actionCMAP.put(actionCommitted.ID_MOVEMENT, Long.toString(actionC.getIdAction()));
            actionCMAP.put(actionCommitted.DATE, actionC.getDate().toString());
            actionCMAP.put(actionCommitted.MOVEMENT, actionC.getActionCommitted());
            actionCMAP.put(actionCommitted.USER_NAME, actionC.getDescription().split(" : ")[0]);
            actionCMAP.put(actionCommitted.DESCRIPTION, actionC.getDescription().split(" : ")[1]);
            return actionCMAP;
        }
        return null;
    }

    /**
     *
     * @param password
     * @param nickName
     * @return
     */
    public boolean validateSession(char[] password, String nickName) {
        User user = consultUser(nickName);
        if (user == null) { //If there isn't an user with the specified nickname.
            return false;
        }
        char[] passwordDB = user.getPassword().toCharArray();
        if (password.length == passwordDB.length) { //If the passwords char arrays are the same lenght
            for (int i = 0; i < password.length; i++) {
                if (passwordDB[i] != password[i]) {
                    return false;
                }
            }
            this.loggedUser = user;//Logged user 
            return true;
        }
        return false;
    }

}
