package org.vn.movieviewer.dto;
// Generated Apr 12, 2018 11:14:16 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysConfig generated by hbm2java
 */
@Entity
@Table(name="sys_config"
)
public class SysConfig  implements java.io.Serializable {


     private Integer idsysApp;
     private String keyCfg;
     private String value1;
     private String value2;

    public SysConfig() {
    }

	
    public SysConfig(String keyCfg) {
        this.keyCfg = keyCfg;
    }
    public SysConfig(String keyCfg, String value1, String value2) {
       this.keyCfg = keyCfg;
       this.value1 = value1;
       this.value2 = value2;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idsys_app", unique=true, nullable=false)
    public Integer getIdsysApp() {
        return this.idsysApp;
    }
    
    public void setIdsysApp(Integer idsysApp) {
        this.idsysApp = idsysApp;
    }

    
    @Column(name="keyCfg", nullable=false, length=45)
    public String getKeyCfg() {
        return this.keyCfg;
    }
    
    public void setKeyCfg(String keyCfg) {
        this.keyCfg = keyCfg;
    }

    
    @Column(name="value1")
    public String getValue1() {
        return this.value1;
    }
    
    public void setValue1(String value1) {
        this.value1 = value1;
    }

    
    @Column(name="value2")
    public String getValue2() {
        return this.value2;
    }
    
    public void setValue2(String value2) {
        this.value2 = value2;
    }




}


