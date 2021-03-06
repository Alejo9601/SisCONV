package Model.DTO;
// Generated Feb 28, 2019 11:12:19 AM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Vehicle generated by hbm2java
 */
public class Vehicle implements java.io.Serializable {

    private Long idVehicle;
    private String domain;
    private String type;
    private String manufacturer;
    private String model;
    private Set agreements = new HashSet(0);

    /**
     * Added by me.
     *
     * @param idVehicle
     */
    public Vehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Vehicle() {
    }

    public Vehicle(String domain, String type, String manufacturer, String model) {
        this.domain = domain;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public Vehicle(String domain, String type, String manufacturer, String model, Set agreements) {
        this.domain = domain;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.agreements = agreements;
    }

    public Long getIdVehicle() {
        return this.idVehicle;
    }

    public void setIdVehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Set getAgreements() {
        return this.agreements;
    }

    public void setAgreements(Set agreements) {
        this.agreements = agreements;
    }

}
