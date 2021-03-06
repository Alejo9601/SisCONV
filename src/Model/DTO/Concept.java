package Model.DTO;
// Generated Feb 28, 2019 11:12:19 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Concept generated by hbm2java
 */
public class Concept implements java.io.Serializable {

    private long idConceptCode;
    private String conceptName;
    private String conceptDescription;
    private Set agreements = new HashSet(0);

    /**
     * Added by me.
     *
     * @param conceptCode
     */
    public Concept(Integer conceptCode) {
        this.idConceptCode = conceptCode;
    }

    /**
     * Added by me.
     *
     * @param idConceptCode
     * @param conceptName
     * @param description
     */
    public Concept(int idConceptCode, String conceptName, String description) {
        this.idConceptCode = idConceptCode;
        this.conceptName = conceptName;
        this.conceptDescription = description;
    }

    /**
     * Adde by me.
     *
     * @return
     */
    public long getConceptID() {
        return this.idConceptCode;
    }

    public Concept() {
    }

    public Concept(long idConceptCode, String conceptName) {
        this.idConceptCode = idConceptCode;
        this.conceptName = conceptName;
    }

    public Concept(long idConceptCode, String conceptName, String conceptDescription, Set agreements) {
        this.idConceptCode = idConceptCode;
        this.conceptName = conceptName;
        this.conceptDescription = conceptDescription;
        this.agreements = agreements;
    }

    public long getIdConceptCode() {
        return this.idConceptCode;
    }

    public void setIdConceptCode(long idConceptCode) {
        this.idConceptCode = idConceptCode;
    }

    public String getConceptName() {
        return this.conceptName;
    }

    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }

    public String getConceptDescription() {
        return this.conceptDescription;
    }

    public void setConceptDescription(String conceptDescription) {
        this.conceptDescription = conceptDescription;
    }

    public Set getAgreements() {
        return this.agreements;
    }

    public void setAgreements(Set agreements) {
        this.agreements = agreements;
    }

}
