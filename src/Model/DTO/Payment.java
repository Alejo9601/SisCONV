package Model.DTO;
// Generated Feb 4, 2019 1:15:39 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Payment generated by hbm2java
 */
public class Payment  implements java.io.Serializable {


     private long idReceiptNumber;
     private Agreement agreement;
     private Date paymentDate;
     private double amount;

    public Payment() {
    }

    public Payment(long idReceiptNumber, Agreement agreement, Date paymentDate, double amount) {
       this.idReceiptNumber = idReceiptNumber;
       this.agreement = agreement;
       this.paymentDate = paymentDate;
       this.amount = amount;
    }
   
    public long getIdReceiptNumber() {
        return this.idReceiptNumber;
    }
    
    public void setIdReceiptNumber(long idReceiptNumber) {
        this.idReceiptNumber = idReceiptNumber;
    }
    public Agreement getAgreement() {
        return this.agreement;
    }
    
    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }
    public Date getPaymentDate() {
        return this.paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public double getAmount() {
        return this.amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }




}


