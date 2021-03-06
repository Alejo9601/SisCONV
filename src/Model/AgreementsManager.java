package Model;

import Model.DTO.Agreement;
import Model.DTO.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

/**
 *
 * @author Alejandro Juarez
 */
public class AgreementsManager {

    UsersManager um = UsersManager.getUsersManager();

    public static enum agreement_param {
        AGREEMENT_NUMBER, FEES_NUMBER, CONCEPT, AMOUNT_OF_DEBT, CREATION_DATE, EXPIRATION_DATE, DESCRIPTION, TAXPAYER,
        VEHICLE, LAND_PROPERTY, STATUS
    };

    public static enum payment_param {
        RECEIPT_NUMBER, AMOUNT, DATE_OF_PAYMENT, AGREEMENT_NUMBER, FEE, PAYMENT_ID
    }

    public static enum agreement_status {
        CANCELLED("CANCELADO"),
        VALID("VIGENTE"),
        WITHOUT_EFFECT("SIN EFECTO");

        private final String value;

        agreement_status(String status) {
            this.value = status;
        }

        public String getValue() {
            return value;
        }
    };

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
        agreement.setStatus(agreement_status.VALID.getValue());
        //Opening Hibernate session.
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            session.insert(agreement);
            transaction.commit();
            //Registering movement
            um.registerUserAction(
                    UsersManager.user_actions.AGREEMENT_REGISTRATION,
                    "Convenio nro. " + agreementMap.get(agreement_param.AGREEMENT_NUMBER));
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
     * Gets the agreement for the number specified.
     *
     * @param agreementNumber
     * @return
     */
    public EnumMap<agreement_param, String> consultAgreement(Long agreementNumber) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Agreement agreement = null;
        try {
            SQLQuery consulta = session.createSQLQuery(
                    "SELECT * FROM agreement WHERE agreement.id_agreementNumber = " + agreementNumber);
            consulta.addEntity(Agreement.class);
            agreement = (Agreement) consulta.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el convenio" + e);
        } finally {
            session.close();
        }
        return agreementOnEnumMap(agreement);
    }

    /**
     * Gets the agreement for agreementNumber like...
     *
     * @param agreementNumber
     * @return
     */
    public List<EnumMap<agreement_param, String>> consultAgreementForNumberLike(long agreementNumber) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        List<Agreement> agreementL = null;
        try {
            SQLQuery consulta = session.createSQLQuery(
                    "SELECT * FROM agreement WHERE agreement.id_agreementNumber LIKE '" + agreementNumber + "%' ORDER BY id_agreementNumber DESC LIMIT 100");
            consulta.addEntity(Agreement.class);
            agreementL = consulta.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el convenio" + e);
        } finally {
            session.close();
        }
        return agreementsOnEnumList(agreementL);
    }

    /**
     * Gets the agreement for the doc number of taxpayer
     *
     * @param taxpayerDocNumber
     * @return
     */
    public List<EnumMap<agreement_param, String>> consultAgreementForTaxpayer(long taxpayerDocNumber) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        List<Agreement> agreementL = null;
        try {
            SQLQuery consulta = session.createSQLQuery(
                    "SELECT * FROM agreement WHERE agreement.taxpayer_id_docNumber LIKE '" + taxpayerDocNumber + "%' ORDER BY id_agreementNumber DESC LIMIT 100");
            consulta.addEntity(Agreement.class);
            agreementL = consulta.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el convenio" + e);
        } finally {
            session.close();
        }
        return agreementsOnEnumList(agreementL);
    }

    /**
     * Gets all the agreements from DB.
     *
     * @param offset
     * @return
     */
    public List<EnumMap<agreement_param, String>> consultAllAgreements(long offset) {
////        HibernateUtil.refreshSessionFactory();
//        HibernateUtil.evict2ndLevelCache();
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        List<Agreement> agreementL = null;
        try {
            SQLQuery consult = session.createSQLQuery(
                    "SELECT * FROM agreement ORDER BY id_agreementNumber DESC LIMIT 100 OFFSET " + offset);
            consult.addEntity(Agreement.class);
            agreementL = consult.list();
            for (Agreement a : agreementL) {
                session.refresh(a);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el padron de convenios" + e);
        } finally {
            session.close();
        }
        return agreementsOnEnumList(agreementL);
    }

    /**
     * Will update an agreement on DB.
     *
     * @return boolean
     * @param agreementMap
     */
    public boolean updateAgreement(EnumMap<agreement_param, String> agreementMap) {
        //Will help to discover the data that has been changed
        Agreement aux = unpackAgreementMap(consultAgreement(Long.parseLong(agreementMap.get(agreement_param.AGREEMENT_NUMBER))));
        //Unpacking all the data.
        Agreement agreement = unpackAgreementMap(agreementMap);
        agreement.setStatus(aux.getStatus());
        //Opening Hibernate session.
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            session.update(agreement);
            transaction.commit();
            //Registering movement
            um.registerUserAction(
                    UsersManager.user_actions.MODIFY_AGREEMENT,
                    "Convenio nro. " + agreementMap.get(agreement_param.AGREEMENT_NUMBER));
        } catch (Exception e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion actulizando los datos del convenio convenio" + e);
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     * Gets agreements count for specified concept
     *
     * @param conceptCode
     * @return
     */
    public long countAgreementsForConcept(Long conceptCode) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        long count = 0;
        try {
            SQLQuery consulta = session.createSQLQuery(
                    "SELECT count(*) FROM agreement WHERE agreement.concept_id_conceptCode = " + conceptCode);
            BigInteger largeN = (BigInteger) consulta.uniqueResult();
            count = largeN.longValue();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando la cuenta de los convenios para el concepto" + e);
        } finally {
            session.close();
        }
        return count;
    }

    /**
     * Gets the agreements count from DB.
     *
     * @return
     */
    public long agreementsCount() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        long count = 0;
        try {
            SQLQuery consulta = session.createSQLQuery("SELECT count(*) FROM agreement");
            BigInteger largeN = (BigInteger) consulta.uniqueResult();
            count = largeN.longValue();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando la cuenta de los convenios para el concepto" + e);
        } finally {
            session.close();
        }
        return count;
    }

//    /**
//     * Gets all the agreements from DB.
//     *
//     * @return
//     */
//    public List<EnumMap<agreement_param, String>> consultAllAgreements() {
//        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
//        List<Agreement> agreementL = null;
//        try {
//            SQLQuery consult = session.createSQLQuery("SELECT * FROM agreement ORDER BY id_agreementNumber DESC");
//            consult.addEntity(Agreement.class);
//            agreementL = consult.list();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Excepcion consultando el padron de convenios" + e);
//        } finally {
//            session.close();
//        }
//        return agreementsOnEnumList(agreementL);
//    }
    /**
     * Gets the next number available for an agreement.
     *
     * @return
     */
    public long consultLastAgreementNumber() {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        long lastNumber = -1; // Will return -1 if something bad happens
        try {
            SQLQuery consult = session.createSQLQuery("SELECT MAX(id_agreementNumber) FROM agreement");
            if (consult.uniqueResult() == null) { //If the result is null there is no agreements on DB.
                lastNumber = 1;
            } else { //If there is at less 1
                lastNumber = ((BigInteger) consult.uniqueResult()).longValue() + 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, "Excepcion consultando el ultimo nro. del convenio",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } finally {
            session.close();
        }
        return lastNumber;
    }

    /**
     * Will leave an agreement without effect.
     *
     * @param agreementNumber
     * @param status
     * @return
     */
    public boolean changeAgreementStatus(Long agreementNumber, agreement_status status) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction transaction = null;
        boolean flag = true;
        try {
            transaction = session.beginTransaction();
            SQLQuery consult = session.createSQLQuery("UPDATE agreement SET status = '"
                    + status.getValue()
                    + "' WHERE agreement.id_agreementNumber = " + agreementNumber);
            consult.executeUpdate();
            transaction.commit();
            if (status.getValue().equals(agreement_status.WITHOUT_EFFECT.value)) {
                um.registerUserAction(UsersManager.user_actions.LEAVE_AGREEMENT_WITHOUT_EFFECT,
                        "Convenio nro. " + agreementNumber);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            flag = false;
            JOptionPane.showMessageDialog(
                    null, "Excepcion al tratar de dejar sin efecto el convenio",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     * Will construct a list of enum maps, each one of them with information of
     * an agreement.
     *
     * @return
     */
    private List<EnumMap<agreement_param, String>> agreementsOnEnumList(List<Agreement> agreementL) {
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
        Agreement agreement = new Agreement(
                Long.parseLong(agreementMap.get(agreement_param.AGREEMENT_NUMBER)),
                Double.parseDouble(agreementMap.get(agreement_param.AMOUNT_OF_DEBT)),
                Integer.parseInt(agreementMap.get(agreement_param.FEES_NUMBER)),
                Date.valueOf(agreementMap.get(agreement_param.CREATION_DATE)),
                Date.valueOf(agreementMap.get(agreement_param.EXPIRATION_DATE)),
                agreementMap.get(agreement_param.DESCRIPTION),
                agreementMap.get(agreement_param.STATUS));
        //Setting the other entities on the agreement, based on their DB keys.
        agreement.setConcept(Integer.parseInt(agreementMap.get(agreement_param.CONCEPT)));
        agreement.setTaxpayer(Long.parseLong(agreementMap.get(agreement_param.TAXPAYER)));
        if ((agreementMap.get(agreement_param.VEHICLE) != null) || (agreementMap.get(agreement_param.LAND_PROPERTY) != null)) {
            if (agreementMap.get(agreement_param.VEHICLE) != null) {
                agreement.setVehicle(Long.parseLong(agreementMap.get(agreement_param.VEHICLE)));
            } else {
                agreement.setLandProperty(Long.parseLong(agreementMap.get(agreement_param.LAND_PROPERTY)));
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
        if (a != null) {
            EnumMap<agreement_param, String> agreementMap = new EnumMap<>(agreement_param.class
            );
            agreementMap.put(agreement_param.AGREEMENT_NUMBER, a.getIdAgreementNumber().toString());
            agreementMap.put(agreement_param.AMOUNT_OF_DEBT, Double.toString(a.getAmountOfDebt()));
            agreementMap.put(agreement_param.CREATION_DATE, a.getCreationDate().toString());
            agreementMap.put(agreement_param.EXPIRATION_DATE, a.getExpirationDate().toString());
            agreementMap.put(agreement_param.FEES_NUMBER, Integer.toString(a.getFeesNumber()));
            agreementMap.put(agreement_param.DESCRIPTION, a.getDescription());
            agreementMap.put(agreement_param.STATUS, a.getStatus());
            agreementMap.put(agreement_param.TAXPAYER, Long.toString(a.getTaxpayerID()));
            agreementMap.put(agreement_param.CONCEPT, Long.toString(a.getConceptID()));
            if (a.getVehicle() != null || a.getLandProperty() != null) {
                if (a.getVehicle() != null) {
                    agreementMap.put(agreement_param.VEHICLE, Long.toString(a.getVehicleID()));
                } else {
                    agreementMap.put(agreement_param.LAND_PROPERTY, Long.toString(a.getLandPropertyID()));
                }
            }
            return agreementMap;
        }
        return null;
    }

    /**
     * Will register a ner receipt on DB.
     */
    private boolean newReceipt(EnumMap<payment_param, String> payMap) {
        Receipt receipt = new Receipt(
                Long.parseLong(payMap.get(payment_param.RECEIPT_NUMBER)),
                Date.valueOf(payMap.get(payment_param.DATE_OF_PAYMENT)),
                Double.parseDouble(payMap.get(payment_param.AMOUNT)));
        //Opening Hibernate session.
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction transaction = null;
        boolean flag = true;
        try {
            transaction = session.beginTransaction();
            session.insert(receipt);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion dando de alta un nuevo recibo" + e);
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     *
     * @return
     */
    private Receipt consultReceipt(Long receiptNumber) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Receipt receipt = null;
        try {
            SQLQuery consulta = session.createSQLQuery(
                    "SELECT * FROM receipt WHERE receipt.id_receiptNumber = " + receiptNumber);
            consulta.addEntity(Receipt.class);
            receipt = (Receipt) consulta.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el recibo del pago" + e);
        } finally {
            session.close();
        }
        return receipt;
    }

    /**
     * Will delete the specified receipt from DB.
     *
     * @param receiptNumber
     * @return
     */
    private boolean deleteReceipt(Long receiptNumber) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            SQLQuery consult = session.createSQLQuery(
                    "DELETE FROM receipt WHERE receipt.id_receiptNumber = " + receiptNumber);
            consult.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion tratando de eliminar el recibo" + e);
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     * Will insert new pay on DB.
     *
     * @param payMap
     * @return
     */
    public boolean newPayment(EnumMap<payment_param, String> payMap) {
        Receipt receipt = consultReceipt(Long.parseLong(payMap.get(payment_param.RECEIPT_NUMBER)));
        Payment pay = unpackPaymentMap(payMap);
        if (receipt == null) {
            if (!newReceipt(payMap)) {
                return false;
            }
        }
        //Opening Hibernate session.
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            session.insert(pay);
            transaction.commit();
            //Registering movement
            um.registerUserAction(
                    UsersManager.user_actions.PAYMENT_REGISTRATION,
                    "Recibo nro. " + pay.getReceipt().getIdReceiptNumber()
                    + " Sobre Convenio nro. " + pay.getAgreement().getIdAgreementNumber());
        } catch (HibernateException e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion dando de alta un nuevo pago" + e);
            flag = false;
        } finally {
            session.close();
        }
        //We verify if this payment is last payment
        if (flag == true && LastPayment(pay.getAgreement().getIdAgreementNumber())) {
            changeAgreementStatus(pay.getAgreement().getIdAgreementNumber(), agreement_status.CANCELLED);
        }
        return flag;
    }

    /**
     * Will consult if there is any payment with the specified receipt number or
     * more than one
     *
     * @param receiptNumber
     * @return
     */
    private List<Payment> consultPaymentsForReceipt(Long receiptNumber) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        List<Payment> paymentL = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM payment WHERE payment.receipt_id_receiptNumber = " + receiptNumber);
            consult.addEntity(Payment.class);
            paymentL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el pago especificado" + e);
        } finally {
            session.close();
        }
        if (paymentL != null) {
            //Consulting receipt information for the payment.
            for (Payment p : paymentL) { //Consulting receipt information for the payment.
                p.setReceipt(consultReceipt(p.getReceipt().getIdReceiptNumber()));
            }
        }
        return paymentL;
    }

    /**
     * Will get all payments for an agreement number from DB.
     *
     * @param agreementNumber
     * @return
     */
    public List<EnumMap<payment_param, String>> consultPaymentsForAgreement(Long agreementNumber) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        List<Payment> paymentL = null;
        try {
            SQLQuery consult = session.createSQLQuery(
                    "SELECT * FROM payment WHERE payment.agreement_id_agreementNumber = " + agreementNumber + " ORDER BY paidFee ASC");
            consult
                    .addEntity(Payment.class
                    );
            paymentL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando los pagos del convenio" + e);
        } finally {
            session.close();
        }
        if (paymentL != null) {
            for (Payment p : paymentL) { //Consulting receipt information for the payment.
                p.setReceipt(consultReceipt(p.getReceipt().getIdReceiptNumber()));
            }
        }
        return paymentsOnEnumList(paymentL);
    }

    /**
     * Will delete a payment with the specified receipt number.
     *
     * @param paymentID
     * @param receiptNumber
     * @return
     */
    public boolean deletePayment(Long paymentID, Long receiptNumber) {
        List<Payment> paymentsL = consultPaymentsForReceipt(receiptNumber);
        if (paymentsL.isEmpty()) {
            return false; //We return false if the are no payments for specified receipt
        }
        //If there are  more than one payment for that receipt.
        if (paymentsL.size() > 1) {
            StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
            Transaction transaction = null;
            boolean flag = true;
            try {
                transaction = session.beginTransaction();
                SQLQuery consult = session.createSQLQuery(
                        "DELETE FROM payment WHERE payment.id_paymentNumber = " + paymentID);
                consult.executeUpdate();
                transaction.commit();
                //Registering movement
                um.registerUserAction(
                        UsersManager.user_actions.DELETE_PAYMENT,
                        "Recibo nro. " + receiptNumber
                        + " Del Convenio nro. " + paymentsL.get(0).getAgreement().getIdAgreementNumber());
            } catch (Exception e) {
                if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                    transaction.rollback();
                }
                JOptionPane.showMessageDialog(null, "Excepcion tratando de eliminar el recibo" + e);
                flag = false;
            } finally {
                session.close();
            }
            //We must change agreement status
            if (flag == true) {
                changeAgreementStatus(paymentsL.get(0).getAgreement().getIdAgreementNumber(), agreement_status.VALID);
            }
            return flag;
        }
        /**
         * Reached this point means this specific payment is related to specific
         * receipt so we can delete it
         */
        if (deleteReceipt(receiptNumber)) {
            changeAgreementStatus(paymentsL.get(0).getAgreement().getIdAgreementNumber(), agreement_status.VALID);
            //Registering movement
            um.registerUserAction(
                    UsersManager.user_actions.DELETE_PAYMENT,
                    "Recibo nro. " + receiptNumber
                    + " Del Convenio nro. " + paymentsL.get(0).getAgreement().getIdAgreementNumber());
            return true;
        }
        return false;
    }

    /**
     *
     * @param payment
     * @param agreementNumber
     * @return
     */
    private boolean LastPayment(Long agreementNumber) {
        Agreement agreement = unpackAgreementMap(consultAgreement(agreementNumber));
        List<Payment> paymentL = unpackPaymentsEnumList(consultPaymentsForAgreement(agreementNumber));
        return agreement.getFeesNumber() == paymentL.size();
    }

    /**
     * Will verify if payment already exists on DB.
     *
     * @param receiptNumber
     * @param agreementNumber
     * @return
     */
    public boolean receiptExistsForAgreement(Long receiptNumber, Long agreementNumber) {
        List<Payment> paymentsL = consultPaymentsForReceipt(receiptNumber);
        if (paymentsL == null) {
            return false;
        }
        for (Payment p : paymentsL) {
            if (Objects.equals(agreementNumber, p.getAgreement().getIdAgreementNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param paymentMap
     * @return
     */
    public boolean paymentExistsForAgreement(EnumMap<payment_param, String> paymentMap) {
        List<EnumMap<payment_param, String>> paymentsEl = consultPaymentsForAgreement(Long.parseLong(paymentMap.get(payment_param.AGREEMENT_NUMBER)));
        for (EnumMap<payment_param, String> p : paymentsEl) {
            if (p.get(payment_param.FEE).equals(paymentMap.get(payment_param.FEE))) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param receiptNumber
     * @return
     */
    public boolean receiptExists(Long receiptNumber) {
        return consultReceipt(receiptNumber) != null;
    }

    /**
     *
     * @param amountOfDebt
     * @param feesNumber
     * @return
     */
    public double feeValueOf(Double amountOfDebt, int feesNumber) {
        return amountOfDebt / feesNumber;
    }

    /**
     * Will
     *
     * @param paymentList
     * @return
     */
    private List<EnumMap<payment_param, String>> paymentsOnEnumList(List<Payment> paymentList) {
        if (paymentList == null) {
            return null;
        }
        List<EnumMap<payment_param, String>> paymentsEL = new ArrayList<>();
        for (Payment p : paymentList) {
            paymentsEL.add(paymentOnEnumMap(p));
        }
        return paymentsEL;
    }

    /**
     *
     * @param paymentsEL
     * @return
     */
    private List<Payment> unpackPaymentsEnumList(List<EnumMap<payment_param, String>> paymentsEL) {
        List<Payment> paymentL = new ArrayList<>();
        for (EnumMap<payment_param, String> p : paymentsEL) {
            Payment payment = unpackPaymentMap(p);
            paymentL.add(payment);
        }
        return paymentL;
    }

    /**
     * Will fill an EnumMap with information about the payment.
     *
     * @param payment
     * @return
     */
    private EnumMap<payment_param, String> paymentOnEnumMap(Payment payment) {
        if (payment == null) {
            return null;

        }
        EnumMap<payment_param, String> paymentMap = new EnumMap<>(payment_param.class
        );
        paymentMap.put(payment_param.PAYMENT_ID, Long.toString(payment.getIdPaymentNumber()));
        paymentMap.put(payment_param.AGREEMENT_NUMBER, Long.toString(payment.getAgreement().getIdAgreementNumber()));
        paymentMap.put(payment_param.RECEIPT_NUMBER, Long.toString(payment.getReceipt().getIdReceiptNumber()));
        paymentMap.put(payment_param.AMOUNT, Double.toString(payment.getReceipt().getAmount()));
        paymentMap.put(payment_param.DATE_OF_PAYMENT, payment.getReceipt().getDate().toString());
        paymentMap.put(payment_param.FEE, Integer.toString(payment.getPaidFee()));
        return paymentMap;
    }

    /**
     * Will extract the pay info from EnumMap.
     *
     * @param payMap
     * @return
     */
    private Payment unpackPaymentMap(EnumMap<payment_param, String> payMap) {
        if (payMap == null) {
            return null;
        }
        Receipt receipt = new Receipt(Long.parseLong(payMap.get(payment_param.RECEIPT_NUMBER)));
        Agreement agreement = new Agreement(Long.parseLong(payMap.get(payment_param.AGREEMENT_NUMBER)));
        Payment pay = new Payment(agreement, receipt, Integer.parseInt(payMap.get(payment_param.FEE)));
        return pay;
    }

    /**
     * Generates a new report with all agreements general info.
     */
    public void agreementsReport() {
        String userName = System.getProperty("user.name");
        String directoryOfSavements = "C:\\Users\\" + userName + "\\Documents\\SisCONV";
        Connection con = null;
        File filePDF = new File(directoryOfSavements + "\\AgreementsPadron.pdf");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sisconv28", "root", "holasoyalej");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reports/AgreementsPadron.jasper"));
            JasperPrint jPrint = JasperFillManager.fillReport(reporte, null, con);
            JasperExportManager.exportReportToPdfFile(jPrint, filePDF.getAbsolutePath());

            try {
                File path = new File(filePDF.getAbsolutePath());
                Desktop.getDesktop().open(path);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Excepcion tratando de visualizar el PDF generado en..."
                        + filePDF.getAbsolutePath());
            }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException | JRException e) {
            JOptionPane.showMessageDialog(null, "Excepcion realzando el reporte del padron general" + e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Excepcion cerrando la conexion del reporte" + ex);
                }
            }
        }
    }

    public void agreementDetails(Long agreementNumber) {
        String userName = System.getProperty("user.name");
        String directoryOfSavements = "C:\\Users\\" + userName + "\\Documents\\SisCONV";
        Connection con = null;
        File filePDF = new File(directoryOfSavements + "\\AgreementDetails.pdf");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sisconv28", "root", "holasoyalej");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reports/AgreementDetail.jasper"));
            HashMap parameters = new HashMap();

            parameters.put("AgreementNumber", agreementNumber);

            JasperPrint jPrint = JasperFillManager.fillReport(reporte, parameters, con);
            JasperExportManager.exportReportToPdfFile(jPrint, filePDF.getAbsolutePath());

            try {
                File path = new File(filePDF.getAbsolutePath());
                Desktop.getDesktop().open(path);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Excepcion tratando de visualizar el PDF generado en..."
                        + filePDF.getAbsolutePath());
            }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException | JRException e) {
            JOptionPane.showMessageDialog(null, "Excepcion realizando el reporte para el convenio" + e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Excepcion cerrando la conexion del reporte" + ex);
                }
            }
        }
    }

}
