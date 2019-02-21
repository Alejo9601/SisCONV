package Model;

import Model.DTO.Concept;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alejandro Juarez.
 */
public class ParametersManager {

    public static enum concept_param {
        CONCEPT_CODE, CONCEPT_NAME, DESCRIPTION
    };

    /**
     * Will add a new concept on DB.
     *
     * @param conceptMap
     * @return
     */
    public boolean newConcept(EnumMap<concept_param, String> conceptMap) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Concept concept = unpackConceptMap(conceptMap);
        boolean flag = true;
        try {
            transaction = session.beginTransaction();
            session.save(concept);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            flag = false;
        } finally {
            session.close();
        }
        return flag;
    }

    /**
     * Gets all the concepts from DB.
     *
     * @return
     */
    public List<EnumMap<concept_param, String>> consultAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Concept> conceptL = null;
        List<EnumMap<concept_param, String>> conceptEL = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM concept");
            consult.addEntity(Concept.class);
            conceptL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando a los contribuyentes" + e);
        } finally {
            session.close();
        }
        /**
         * If the list has at least one concept, then we unpack and put them
         * into a list of EnumMap<concept_param, String>, so we can return it.
         */
        if (conceptL != null) {
            conceptEL = new ArrayList<>();
            for (Concept cn : conceptL) {
                conceptEL.add(conceptOnEnumMap(cn));
            }
        }
        return conceptEL;
    }

    /**
     * Gets a concept from DB.
     *
     * @param conceptCode
     * @return
     */
    public EnumMap<concept_param, String> consult(int conceptCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Concept concept = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM concept WHERE concept.id_conceptCode = " + conceptCode);
            consult.addEntity(Concept.class);
            concept = (Concept) consult.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando a los contribuyentes" + e);
        } finally {
            session.close();
        }
        return conceptOnEnumMap(concept);
    }

    /**
     * Will extract the concept data from EnumMap.
     *
     * @return
     */
    private Concept unpackConceptMap(EnumMap<concept_param, String> conceptParam) {
        Concept concept = new Concept(Integer.parseInt(conceptParam.get(concept_param.CONCEPT_CODE)),
                conceptParam.get(concept_param.CONCEPT_NAME),
                conceptParam.get(concept_param.DESCRIPTION));
        return concept;
    }

    /**
     * Constructs an EnumMap with the concept information.
     *
     * @param cn
     * @return
     */
    public EnumMap<concept_param, String> conceptOnEnumMap(Concept cn) {
        if (cn != null) {
            EnumMap<concept_param, String> conceptMap = new EnumMap<>(concept_param.class);
            conceptMap.put(concept_param.CONCEPT_NAME, cn.getConceptName());
            conceptMap.put(concept_param.CONCEPT_CODE, Integer.toString(cn.getIdConceptCode()));
            conceptMap.put(concept_param.DESCRIPTION, cn.getDescription());
            return conceptMap;
        }
        return null;
    }

}
