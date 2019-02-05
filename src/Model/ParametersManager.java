package Model;

import Model.DTO.Concept;
import Model.DTO.HibernateUtil;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Alejandro Juarez.
 */
public class ParametersManager {

    public static enum concept_param {
        CONCEPT_CODE, CONCEPT_NAME, DESCRIPTION
    };

    public List<EnumMap<concept_param, String>> consultAllConcepts() {
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
                EnumMap<concept_param, String> conceptMap = new EnumMap<>(concept_param.class);
                conceptMap.put(concept_param.CONCEPT_NAME, cn.getConceptName());
                conceptMap.put(concept_param.CONCEPT_CODE, Integer.toString(cn.getIdConceptCode()));
                conceptMap.put(concept_param.DESCRIPTION, cn.getDescription());
                conceptEL.add(conceptMap);
            }
        }

        return conceptEL;
    }

}
