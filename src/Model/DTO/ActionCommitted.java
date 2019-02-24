package Model.DTO;
// Generated Feb 22, 2019 2:08:50 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * ActionCommitted generated by hbm2java
 */
public class ActionCommitted  implements java.io.Serializable {


     private Long idAction;
     private String actionCommitted;
     private Date date;
     private String description;

    public ActionCommitted() {
    }

    public ActionCommitted(String actionCommitted, Date date, String description) {
       this.actionCommitted = actionCommitted;
       this.date = date;
       this.description = description;
    }
   
    public Long getIdAction() {
        return this.idAction;
    }
    
    public void setIdAction(Long idAction) {
        this.idAction = idAction;
    }
    public String getActionCommitted() {
        return this.actionCommitted;
    }
    
    public void setActionCommitted(String actionCommitted) {
        this.actionCommitted = actionCommitted;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


