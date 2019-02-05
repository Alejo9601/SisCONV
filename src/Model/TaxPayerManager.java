package Model;

import Model.DTO.HibernateUtil;
import Model.DTO.Taxpayer;
import java.util.ArrayList;
import java.util.List;
import java.util.EnumMap;
import javax.swing.JOptionPane;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Alejandro Juarez
 */
public class TaxPayerManager {

    public static enum taxpayer_param {
        NAMES, LASTNAME, DOC_NUMBER, DOC_TYPE, ADDRESS, PHONE_NUMBER,
    };

    /**
     * Gets all the taxpayers from DB.
     *
     * @return
     */
    public List<EnumMap<taxpayer_param, String>> consultAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Taxpayer> taxpayerL = null;
        List<EnumMap<taxpayer_param, String>> taxpayersEL = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM taxpayer");
            consult.addEntity(Taxpayer.class);
            taxpayerL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando a los contribuyentes" + e);
        } finally {
            session.close();
        }
        if (taxpayerL != null) {
            taxpayersEL = new ArrayList<>();
            for (Taxpayer tx : taxpayerL) {
                taxpayersEL.add(TaxpayerOnEnumMap(tx));
            }
        }
        return taxpayersEL;
    }

    /**
     * Gets taxpayer information asociated to the document number.
     *
     * @param taxpayerNroDoc
     * @return
     */
    public EnumMap<taxpayer_param, String> consult(Long taxpayerNroDoc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Taxpayer tp = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM taxpayer WHERE taxpayer.id_docNumber = " + taxpayerNroDoc);
            consult.addEntity(Taxpayer.class);
            tp = (Taxpayer) consult.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el contribuyente" + e);
        } finally {
            session.close();
        }
        return TaxpayerOnEnumMap(tp);
    }

    /**
     * Will construct an enum map with taxpayer information.
     *
     * @return
     */
    private EnumMap<taxpayer_param, String> TaxpayerOnEnumMap(Taxpayer tp) {
        if (tp != null) {
            EnumMap<taxpayer_param, String> taxpayerMap = new EnumMap<>(taxpayer_param.class);
            taxpayerMap.put(taxpayer_param.NAMES, tp.getName());
            taxpayerMap.put(taxpayer_param.LASTNAME, tp.getLastName());
            taxpayerMap.put(taxpayer_param.DOC_NUMBER, Long.toString(tp.getIdDocNumber()));
            taxpayerMap.put(taxpayer_param.DOC_TYPE, tp.getDocType());
            taxpayerMap.put(taxpayer_param.ADDRESS, tp.getAddress());
            taxpayerMap.put(taxpayer_param.PHONE_NUMBER, tp.getPhoneNumber());
            return taxpayerMap;
        }
        return null;
    }

}
