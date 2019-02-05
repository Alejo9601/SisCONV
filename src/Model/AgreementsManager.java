package Model;

import Model.DTO.Agreement;
import Model.DTO.*;
import java.math.BigInteger;
import java.sql.Date;
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
public class AgreementsManager {

    public static enum agreement_param {
        AGREEMENT_NUMBER, CUOTS_NUMBER, CONCEPT_CODE, AMOUNT_OF_DEBT, CREATION_DATE, EXPIRATION_DATE, DESCRIPTION, TAXPAYER_DNI,
        VEHICLE_DOMAIN, PROPERTY_DECRET, STATE
    };

    public static enum agreement_states {
        CANCELLED, VALID, WITHOUT_EFFECT
    };

    //public static EnumMap<agreement_param, Object> agreement_map = new EnumMap<>(agreement_param.class);//public static EnumMap<agreement_param, Object> agreement_map = new EnumMap<>(agreement_param.class);
    /**
     * Constructor of the class, will initiate all the DAO objects to manipulate
     * the data on DB.
     */
    public AgreementsManager() {

    }

    /**
     * Will insert a new agreement on DB.
     *
     * @return boolean
     * @param agreementMap
     */
    public boolean newAgreement(EnumMap<agreement_param, String> agreementMap) {
        //Unpacking all the data.
        Agreement agreement = unpackAgreementMap(agreementMap);
        //The agreement initially is valid.
        agreement.setState(agreement_states.VALID.toString());
        //Opening Hibernate session.
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            session.save(agreement);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion dando de alta un nuevo convenio" + e);
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     * Gets all the agreements from DB.
     *
     * @return
     */
    public List<EnumMap<agreement_param, String>> consultAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Agreement> agreementL = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM agreement");
            consult.addEntity(Agreement.class);
            agreementL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el padron de convenios" + e);
        } finally {
            session.close();
        }
        return agreementOnEnumList(agreementL);
    }

    /**
     * Gets the agreement for the number specified.
     *
     * @param agreementNumber
     * @return
     */
    public EnumMap<agreement_param, String> consult(Long agreementNumber) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Agreement agreement = null;
        try {
            SQLQuery consulta = session.createSQLQuery(
                    "SELECT * FROM agreement WHERE agreemet.id_agreementNumber = " + agreementNumber);
            consulta.addEntity(Agreement.class);
            agreement = (Agreement) consulta.uniqueResult();
        } catch (Exception e) {
        }
        return agreementOnEnumMap(agreement);
    }

    /**
     * Will construct a list of enum maps, each one of them with information of
     * an agreement.
     *
     * @return
     */
    private List<EnumMap<agreement_param, String>> agreementOnEnumList(List<Agreement> agreementL) {
        if (agreementL != null) {
            List<EnumMap<agreement_param, String>> agreementEL = new ArrayList<>();
            for (Agreement a : agreementL) {
                agreementEL.add(agreementOnEnumMap(a));
            }
            return agreementEL;
        }
        return null;
    }

    /**
     * Will unpack the information of an agreement and build its DTO whit it.
     *
     * @param agreementMap
     * @return
     */
    private Agreement unpackAgreementMap(EnumMap<agreement_param, String> agreementMap) {
        Agreement agreement = new Agreement(Double.parseDouble(agreementMap.get(agreement_param.AMOUNT_OF_DEBT)),
                Integer.parseInt(agreementMap.get(agreement_param.CUOTS_NUMBER)),
                Date.valueOf(agreementMap.get(agreement_param.CREATION_DATE)),
                Date.valueOf(agreementMap.get(agreement_param.EXPIRATION_DATE)),
                agreementMap.get(agreement_param.STATE));

        //Setting the other entities on the agreement, based on their DB keys.
        agreement.setConcept(Integer.parseInt(agreementMap.get(agreement_param.CONCEPT_CODE)));
        agreement.setTaxpayer(Long.parseLong(agreementMap.get(agreement_param.TAXPAYER_DNI)));

        if ((agreementMap.get(agreement_param.VEHICLE_DOMAIN) != null) || (agreementMap.get(agreement_param.VEHICLE_DOMAIN) != null)) {
            if (agreementMap.get(agreement_param.VEHICLE_DOMAIN) != null) {
                agreement.setVehicle(agreementMap.get(agreement_param.VEHICLE_DOMAIN));
            } else {
//                agreement.setProperty();
            }
        }
        return agreement;
    }

    /**
     * Will construct an EnumMap with the information of the agreement.
     *
     * @param a
     * @return
     */
    public EnumMap<agreement_param, String> agreementOnEnumMap(Agreement a) {
        EnumMap<agreement_param, String> agreementMap = new EnumMap<>(agreement_param.class);
        agreementMap.put(agreement_param.AGREEMENT_NUMBER, a.getIdAgreementNumber().toString());
        agreementMap.put(agreement_param.AMOUNT_OF_DEBT, Double.toString(a.getAmountOfDebt()));
        agreementMap.put(agreement_param.CREATION_DATE, a.getCreationDate().toString());
        agreementMap.put(agreement_param.EXPIRATION_DATE, a.getExpirationDate().toString());
        agreementMap.put(agreement_param.CUOTS_NUMBER, Integer.toString(a.getCuotsNumber()));
        agreementMap.put(agreement_param.DESCRIPTION, a.getDescription());
        agreementMap.put(agreement_param.STATE, a.getState());
        agreementMap.put(agreement_param.TAXPAYER_DNI, Long.toString(a.getTaxPayerID()));
        return agreementMap;
    }

    /**
     * Gets the next number available for an agreement.
     *
     * @return
     */
    public long consultLastAgreementNumber() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long lastNumber = -1; // Will return -1 if something bad happens
        try {
            SQLQuery consult = session.createSQLQuery("SELECT MAX(id_agreementNumber) FROM agreement");
            if (consult.uniqueResult() == null) { //If the result is null there is no agreements on DB.
                lastNumber = 1;
            } else { //If there is at less 1
                lastNumber = ((BigInteger) consult.uniqueResult()).longValue() + 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Advertencia", "Excepcion consultando el ultimo nro. del convenio", JOptionPane.WARNING_MESSAGE);
        } finally {
            session.close();
        }
        return lastNumber;
    }

}
