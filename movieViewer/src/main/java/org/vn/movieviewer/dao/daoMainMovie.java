/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.dao;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.vn.movieviewer.database.HibernateUtil;
import org.vn.movieviewer.dto.MainMovie;

/**
 *
 * @author danglph
 */
public class daoMainMovie {
    
    private static volatile SessionFactory sf;// = HibernateUtil.getSessionFactory();
    private static final Logger logger = Logger.getLogger(daoMainMovie.class);
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        getRowCount();
    }
    
    public static synchronized List<MainMovie> get(boolean isSort, String sortStr, int start, int maxResult) {
        List<MainMovie> result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            String queryStr = "from MainMovie ";
            if(isSort){
                queryStr += "ORDER BY " + sortStr;
            }
            Query q = s.createQuery(queryStr);
            q.setFirstResult(start);
            q.setMaxResults(maxResult);
            result = (List<MainMovie>) q.list();
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

    public static synchronized List<Integer> getIDList() {
        List<Integer> result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            result = s.createQuery("SELECT m.idmainMovie FROM MainMovie m").list();
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

    public static synchronized int getRowCount() {
        int result = 0;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            Object o = s.createQuery("SELECT COUNT (m) FROM MainMovie m").uniqueResult();
            result = ((Long)o).intValue(); 
            s.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error(e.getCause());
            HibernateUtil.closeSessionFactory();
            result = 0;
        } finally {
        }
        return result;
    }

    public static synchronized List<MainMovie> get() {
        List<MainMovie> result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            result = (List<MainMovie>) s.createQuery("from MainMovie").list();
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

    public static int insert(List<MainMovie> lstMainMovie) {
        List<MainMovie> result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        int numbSuccess = 0;
        try {
            s = sf.openSession();
            s.beginTransaction();
            for (MainMovie o : lstMainMovie) {
                MainMovie l = (MainMovie)s.createQuery("From MainMovie WHERE name =:name")
                        .setParameter("name", o.getName()).uniqueResult();
                if(l == null){
                    logger.debug("Insert: " + o.getName());
                    s.save(o);
                    numbSuccess++;
                }else{
                    logger.debug("Exist: " + o.getName());
                }
            }
            s.getTransaction().commit();
            s.clear();
            s.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error(e.getCause());
            HibernateUtil.closeSessionFactory();
            result = null;
        } finally {
        }
        return numbSuccess;
    }

    public static void update(MainMovie movieSelected) {
        
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            s.beginTransaction();
            movieSelected.setUpdateDate(new Date());
            s.saveOrUpdate(movieSelected);
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
