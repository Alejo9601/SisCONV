package Model;

import Model.DTO.User;
import java.util.ArrayList;
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
//            SQLQuery consult = session.createSQLQuery(
//                    "UPDATE user SET activeSession = " + (byte) 1 + " WHERE user.idUser = " + loggedUser.getIdUser());
//            consult.executeUpdate();
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
    /**
     *
     * @param password
     * @param nickName
     * @return
     */
    public boolean validateSession(char[] password, String nickName) {
        User user = consult(nickName);
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

    /**
     * Will consult an user from DB.
     *
     * @param nickname
     * @return
     */
    private User consult(String nickname) {
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
    public List<EnumMap<user_param, String>> consultAll() {
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
                usersEL.add(userOnEnumMap(u));
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

}
