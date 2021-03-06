package Model.DTO;
// Generated Feb 28, 2019 11:12:19 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Agreement generated by hbm2java
 */
public class Agreement implements java.io.Serializable {

    private Long idAgreementNumber;
    private Concept concept;
    private LandProperty landProperty;
    private Taxpayer taxpayer;
    private Vehicle vehicle;
    private double amountOfDebt;
    private int feesNumber;
    private Date creationDate;
    private Date expirationDate;
    private String status;
    private String description;
    private Set payments = new HashSet(0);

    /**
     * Added by me.
     *
     * @param idAgreementNumber
     * @param amountOfDebt
     * @param feesNumber
     * @param creationDate
     * @param expirationDate
     * @param description
     * @param status
     */
    public Agreement(Long idAgreementNumber, double amountOfDebt, int feesNumber, Date creationDate, Date expirationDate, String description, String status) {
        this.idAgreementNumber = idAgreementNumber;
        this.amountOfDebt = amountOfDebt;
        this.feesNumber = feesNumber;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.description = description;
        this.status = status;
    }

    /**
     * Added by me.
     *
     * @param agreementNumber
     */
    public Agreement(Long agreementNumber) {
        this.idAgreementNumber = agreementNumber;
    }

    /**
     * Added by me
     *
     * @param conceptCode
     */
    public void setConcept(Integer conceptCode) {
        this.concept = new Concept(conceptCode);
    }

    /**
     * Added by me.
     *
     * @param taxPayerDocNumeber
     */
    public void setTaxpayer(Long taxPayerDocNumeber) {
        this.taxpayer = new Taxpayer(taxPayerDocNumeber);
    }

    /**
     * Added by me.
     *
     * @param vehicleIdentifier
     */
    public void setVehicle(Long vehicleIdentifier) {
        this.vehicle = new Vehicle(vehicleIdentifier);
    }

    /**
     * Added by me.
     *
     * @param landPropertyIdentifier
     */
    public void setLandProperty(Long landPropertyIdentifier) {
        this.landProperty = new LandProperty(landPropertyIdentifier);
    }

    /**
     * Added by me.
     *
     * @return
     */
    public Long getTaxpayerID() {
        return this.taxpayer.getIdDocNumber();
    }

    /**
     * Added by me.
     *
     * @return
     */
    public long getConceptID() {
        return this.concept.getIdConceptCode();
    }

    /**
     * Added by me.
     *
     * @return
     */
    public Long getVehicleID() {
        return this.vehicle.getIdVehicle();
    }

    /**
     * Added by me.
     *
     * @return
     */
    public Long getLandPropertyID() {
        return this.landProperty.getIdProperty();
    }

    public Agreement() {
    }

    public Agreement(Concept concept, Taxpayer taxpayer, double amountOfDebt, int feesNumber, Date creationDate, Date expirationDate, String status) {
        this.concept = concept;
        this.taxpayer = taxpayer;
        this.amountOfDebt = amountOfDebt;
        this.feesNumber = feesNumber;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    public Agreement(Concept concept, LandProperty landProperty, Taxpayer taxpayer, Vehicle vehicle, double amountOfDebt, int feesNumber, Date creationDate, Date expirationDate, String status, String description, Set payments) {
        this.concept = concept;
        this.landProperty = landProperty;
        this.taxpayer = taxpayer;
        this.vehicle = vehicle;
        this.amountOfDebt = amountOfDebt;
        this.feesNumber = feesNumber;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.description = description;
        this.payments = payments;
    }

    public Long getIdAgreementNumber() {
        return this.idAgreementNumber;
    }

    public void setIdAgreementNumber(Long idAgreementNumber) {
        this.idAgreementNumber = idAgreementNumber;
    }

    public Concept getConcept() {
        return this.concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public LandProperty getLandProperty() {
        return this.landProperty;
    }

    public void setLandProperty(LandProperty landProperty) {
        this.landProperty = landProperty;
    }

    public Taxpayer getTaxpayer() {
        return this.taxpayer;
    }

    public void setTaxpayer(Taxpayer taxpayer) {
        this.taxpayer = taxpayer;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getAmountOfDebt() {
        return this.amountOfDebt;
    }

    public void setAmountOfDebt(double amountOfDebt) {
        this.amountOfDebt = amountOfDebt;
    }

    public int getFeesNumber() {
        return this.feesNumber;
    }

    public void setFeesNumber(int feesNumber) {
        this.feesNumber = feesNumber;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set getPayments() {
        return this.payments;
    }

    public void setPayments(Set payments) {
        this.payments = payments;
    }

}
