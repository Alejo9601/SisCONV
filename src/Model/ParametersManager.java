package Model;

import Model.DTO.Concept;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.SQLQuery;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

/**
 *
 * @author Alejandro Juarez.
 */
public class ParametersManager {

    public static enum concept_param {
        CONCEPT_CODE, CONCEPT_NAME, DESCRIPTION
    };

    private static enum default_concepts {
        LAND_SALESMENT(1201),
        REAL_STATE_TAXES(110101),
        RESIDUE_RECOLECTION(110201),
        VEHICLE_DOMAIN(110104);

        private final long code;

        default_concepts(long code) {
            this.code = code;
        }

        public long getCode() {
            return this.code;
        }
    }

    /**
     *
     * @param code
     * @return
     */
    public boolean isDefaultConcept(Long code) {
        for (default_concepts c : default_concepts.values()) {
            if (c.getCode() == code) {
                return true;
            }
        }
        return false;
    }

    /**
     * Will add a new concept on DB.
     *
     * @param conceptMap
     * @return
     */
    public boolean newConcept(EnumMap<concept_param, String> conceptMap) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction transaction = null;
        Concept concept = unpackConceptMap(conceptMap);
        boolean flag = true;
        try {
            transaction = session.beginTransaction();
            session.insert(concept);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion registrando el concepto" + e);
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
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        List<Concept> conceptL = null;
        List<EnumMap<concept_param, String>> conceptEL = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM concept");
            consult.addEntity(Concept.class);
            conceptL = consult.list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando los conceptos" + e);
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
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Concept concept = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM concept WHERE concept.id_conceptCode = " + conceptCode);
            consult.addEntity(Concept.class);
            concept = (Concept) consult.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el concepto" + e);
        } finally {
            session.close();
        }
        return conceptOnEnumMap(concept);
    }

    /**
     * Gets a concept from DB.
     *
     * @param conceptName
     * @return
     */
    public EnumMap<concept_param, String> consult(String conceptName) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Concept concept = null;
        try {
            SQLQuery consult = session.createSQLQuery("SELECT * FROM concept WHERE concept.conceptName = '" + conceptName + "'");
            consult.addEntity(Concept.class);
            concept = (Concept) consult.uniqueResult();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Excepcion consultando el concepto" + e);
        } finally {
            session.close();
        }
        return conceptOnEnumMap(concept);
    }

    /**
     *
     * @param conceptCode
     * @return
     */
    public boolean deleteConcept(long conceptCode) {
        StatelessSession session = HibernateUtil.getSessionFactory().openStatelessSession();
        Transaction transaction = null;
        boolean flag = true; //Flag that indicates if the operation finished succesfully.
        try {
            transaction = session.beginTransaction();
            SQLQuery consult = session.createSQLQuery(
                    "DELETE FROM concept WHERE concept.id_conceptCode = " + conceptCode);
            consult.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) { //If transaction didnt went well, we roll back any action en DB
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(null, "Excepcion tratando de eliminar el concepto" + e);
            flag = false;
        } finally {
            session.close();
        }
        return flag;
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
            conceptMap.put(concept_param.CONCEPT_CODE, Long.toString(cn.getIdConceptCode()));
            conceptMap.put(concept_param.DESCRIPTION, cn.getConceptDescription());
            return conceptMap;
        }
        return null;
    }

}
