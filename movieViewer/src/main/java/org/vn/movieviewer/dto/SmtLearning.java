package org.vn.movieviewer.dto;
// Generated Apr 12, 2018 11:14:16 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SmtLearning generated by hbm2java
 */
@Entity
@Table(name="smt_learning"
)
public class SmtLearning  implements java.io.Serializable {


     private Integer idsmtLearning;
     private String smtAction;
     private String smtValue1;
     private String smtValue2;

    public SmtLearning() {
    }

	
    public SmtLearning(String smtAction) {
        this.smtAction = smtAction;
    }
    public SmtLearning(String smtAction, String smtValue1, String smtValue2) {
       this.smtAction = smtAction;
       this.smtValue1 = smtValue1;
       this.smtValue2 = smtValue2;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idsmt_learning", unique=true, nullable=false)
    public Integer getIdsmtLearning() {
        return this.idsmtLearning;
    }
    
    public void setIdsmtLearning(Integer idsmtLearning) {
        this.idsmtLearning = idsmtLearning;
    }

    
    @Column(name="smt_action", nullable=false, length=45)
    public String getSmtAction() {
        return this.smtAction;
    }
    
    public void setSmtAction(String smtAction) {
        this.smtAction = smtAction;
    }

    
    @Column(name="smt_value1", length=16777215)
    public String getSmtValue1() {
        return this.smtValue1;
    }
    
    public void setSmtValue1(String smtValue1) {
        this.smtValue1 = smtValue1;
    }

    
    @Column(name="smt_value2", length=45)
    public String getSmtValue2() {
        return this.smtValue2;
    }
    
    public void setSmtValue2(String smtValue2) {
        this.smtValue2 = smtValue2;
    }




}


