package Model.DTO;
// Generated Feb 7, 2019 9:37:40 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Taxpayer generated by hbm2java
 */
public class Taxpayer implements java.io.Serializable {

    private long idDocNumber;
    private String name;
    private String lastName;
    private String lastNameMother;
    private String docType;
    private String address;
    private String phoneNumber;
    private Set agreements = new HashSet(0);

    /**
     * Added by me.
     *
     * @param idDocNumber
     */
    public Taxpayer(Long idDocNumber) {
        this.idDocNumber = idDocNumber;
    }

    /**
     * Added by me.
     *
     * @param idDocNumber
     * @param name
     * @param lastName
     * @param docType
     * @param address
     * @param phoneNumber
     */
    public Taxpayer(long idDocNumber, String name, String lastName, String docType, String address, String phoneNumber) {
        this.idDocNumber = idDocNumber;
        this.name = name;
        this.lastName = lastName;
        this.docType = docType;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Taxpayer() {
    }

    public Taxpayer(long idDocNumber, String name, String lastName, String lastNameMother, String docType, String address, String phoneNumber, Set agreements) {
        this.idDocNumber = idDocNumber;
        this.name = name;
        this.lastName = lastName;
        this.lastNameMother = lastNameMother;
        this.docType = docType;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.agreements = agreements;
    }

    public long getIdDocNumber() {
        return this.idDocNumber;
    }

    public void setIdDocNumber(long idDocNumber) {
        this.idDocNumber = idDocNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameMother() {
        return this.lastNameMother;
    }

    public void setLastNameMother(String lastNameMother) {
        this.lastNameMother = lastNameMother;
    }

    public String getDocType() {
        return this.docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set getAgreements() {
        return this.agreements;
    }

    public void setAgreements(Set agreements) {
        this.agreements = agreements;
    }

}
