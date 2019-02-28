package Model.DTO;
// Generated Feb 28, 2019 11:12:19 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Taxpayer generated by hbm2java
 */
public class Taxpayer implements java.io.Serializable {

    private long idDocNumber;
    private String names;
    private String lastname;
    private String lastnameMother;
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
     * @param names
     * @param lastname
     * @param docType
     * @param address
     * @param phoneNumber
     */
    public Taxpayer(long idDocNumber, String names, String lastname, String docType, String address, String phoneNumber) {
        this.idDocNumber = idDocNumber;
        this.names = names;
        this.lastname = lastname;
        this.docType = docType;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Taxpayer() {
    }

    public Taxpayer(long idDocNumber, String names, String lastname, String docType) {
        this.idDocNumber = idDocNumber;
        this.names = names;
        this.lastname = lastname;
        this.docType = docType;
    }

    public Taxpayer(long idDocNumber, String names, String lastname, String lastnameMother, String docType, String address, String phoneNumber, Set agreements) {
        this.idDocNumber = idDocNumber;
        this.names = names;
        this.lastname = lastname;
        this.lastnameMother = lastnameMother;
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

    public String getNames() {
        return this.names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastnameMother() {
        return this.lastnameMother;
    }

    public void setLastnameMother(String lastnameMother) {
        this.lastnameMother = lastnameMother;
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
