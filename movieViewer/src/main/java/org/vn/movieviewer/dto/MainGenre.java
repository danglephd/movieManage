package org.vn.movieviewer.dto;
// Generated Jan 17, 2018 10:54:29 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MainGenre generated by hbm2java
 */
@Entity
@Table(name="main_genre"
    ,catalog="movies_db"
)
public class MainGenre  implements java.io.Serializable {


     private Integer idmainGenre;
     private String name;

    public MainGenre() {
    }

    public MainGenre(String name) {
       this.name = name;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idmain_genre", unique=true, nullable=false)
    public Integer getIdmainGenre() {
        return this.idmainGenre;
    }
    
    public void setIdmainGenre(Integer idmainGenre) {
        this.idmainGenre = idmainGenre;
    }

    
    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }




}


