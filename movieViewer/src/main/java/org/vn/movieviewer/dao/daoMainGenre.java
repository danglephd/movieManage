/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.dao;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.vn.movieviewer.database.HibernateUtil;
import org.vn.movieviewer.dto.MainGenre;

/**
 *
 * @author danglph
 */
public class daoMainGenre {
    
    private static volatile SessionFactory sf;// = HibernateUtil.getSessionFactory();
    private static final Logger logger = Logger.getLogger(daoMainGenre.class);
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

//        get();
        insert("MMMM");
    }
    

    public static MainGenre getByName(String genreName) {
        MainGenre result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            result = (MainGenre)s.createQuery("From MainGenre WHERE name =:name")
                    .setParameter("name", genreName).uniqueResult();
            s.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error(e.getCause());
            HibernateUtil.closeSessionFactory();
            result = null;
        } finally {
        }
        return result;
    }
    
    public static synchronized List<MainGenre> get() {
        List<MainGenre> result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            result = (List<MainGenre>) s.createQuery("from MainGenre").list();
            s.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error(e.getCause());
            HibernateUtil.closeSessionFactory();
            result = null;
        } finally {
        }
        return result;
    }

    public static boolean insert(String genreName) {
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        boolean isSuccess = false;
//        int numbSuccess = 0;
        try {
            s = sf.openSession();
            s.beginTransaction();
            MainGenre l = (MainGenre)s.createQuery("From MainGenre WHERE name =:name")
                    .setParameter("name", genreName).uniqueResult();
            if(l == null){
                logger.debug("Insert: " + genreName);
                l = new MainGenre(genreName);
                s.save(l);
                isSuccess = true;
            }else{
                logger.debug("Exist: " + genreName);
                isSuccess = false;
            }
            s.getTransaction().commit();
            s.clear();
            s.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error(e.getCause());
            HibernateUtil.closeSessionFactory();
        }
        return isSuccess;
    }
//    public static int insert(List<MainGenre> lstMainGenre) {
//        List<MainGenre> result = null;
//        sf = HibernateUtil.getSessionFactory();
//        Session s = null;
//        int numbSuccess = 0;
//        try {
//            s = sf.openSession();
//            s.beginTransaction();
//            for (MainGenre o : lstMainGenre) {
//                MainGenre l = (MainGenre)s.createQuery("From MainGenre WHERE name =:name")
//                        .setParameter("name", o.getName()).uniqueResult();
//                if(l == null){
//                    logger.debug("Insert: " + o.getName());
//                    s.save(o);
//                    numbSuccess++;
//                }else{
//                    logger.debug("Exist: " + o.getName());
//                }
//            }
//            s.getTransaction().commit();
//            s.clear();
//            s.close();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            logger.error(e.getCause());
//            HibernateUtil.closeSessionFactory();
//            result = null;
//        } finally {
//        }
//        return numbSuccess;
//    }

    public static void update(MainGenre genreSelected) {
        
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            s.beginTransaction();
            s.saveOrUpdate(genreSelected);
            s.getTransaction().commit();
            s.clear();
            s.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error(e.getCause());
            HibernateUtil.closeSessionFactory();
        } finally {
        }
    }
}
