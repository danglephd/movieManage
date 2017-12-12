package org.vn.movieviewer.dto;
// Generated Dec 1, 2017 10:54:07 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SysUser generated by hbm2java
 */
@Entity
@Table(name="sys_user"
)
public class SysUser  implements java.io.Serializable {


    @Id
    @Column(name="idsys_user", unique=true, nullable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer idsysUser;
    @Column(name="username", nullable=false)
     private String username;
@Column(name="password", nullable=false, length=45)
     private String password;

    public SysUser() {
    }

    public SysUser(String username, String password) {
       this.username = username;
       this.password = password;
    }
   
    public Integer getIdsysUser() {
        return this.idsysUser;
    }
    
    public void setIdsysUser(Integer idsysUser) {
        this.idsysUser = idsysUser;
    }

    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}

