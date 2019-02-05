package Model.DTO;
// Generated Feb 4, 2019 1:15:39 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Property generated by hbm2java
 */
public class Property  implements java.io.Serializable {


     private long idProperty;
     private int appleNumber;
     private int lotNumber;
     private String adjudicationDecree;
     private Set agreements = new HashSet(0);

    public Property() {
    }

	
    public Property(long idProperty, int appleNumber, int lotNumber) {
        this.idProperty = idProperty;
        this.appleNumber = appleNumber;
        this.lotNumber = lotNumber;
    }
    public Property(long idProperty, int appleNumber, int lotNumber, String adjudicationDecree, Set agreements) {
       this.idProperty = idProperty;
       this.appleNumber = appleNumber;
       this.lotNumber = lotNumber;
       this.adjudicationDecree = adjudicationDecree;
       this.agreements = agreements;
    }
   
    public long getIdProperty() {
        return this.idProperty;
    }
    
    public void setIdProperty(long idProperty) {
        this.idProperty = idProperty;
    }
    public int getAppleNumber() {
        return this.appleNumber;
    }
    
    public void setAppleNumber(int appleNumber) {
        this.appleNumber = appleNumber;
    }
    public int getLotNumber() {
        return this.lotNumber;
    }
    
    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }
    public String getAdjudicationDecree() {
        return this.adjudicationDecree;
    }
    
    public void setAdjudicationDecree(String adjudicationDecree) {
        this.adjudicationDecree = adjudicationDecree;
    }
    public Set getAgreements() {
        return this.agreements;
    }
    
    public void setAgreements(Set agreements) {
        this.agreements = agreements;
    }




}

