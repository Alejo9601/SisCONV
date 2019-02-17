package Model;

import Model.DTO.HibernateUtil;
import Model.DTO.User;
import java.util.EnumMap;
import javax.swing.JOptionPane;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alejandro Juarez
 */
public class UsersManager {
    
    User loggedUser;
    
    public static enum user_param {
        NAMES, LASTNAME, NICKNAME, PASSWORD, ADMINISTRATOR, ID_USER
    };
    
    public User getLoggedUser() {
        return loggedUser;
    }

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
            this.loggedUser = user;
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
    
    
    
}
