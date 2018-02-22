/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.vn.movieviewer.database.HibernateUtil;
import org.vn.movieviewer.dto.SysConfig;

/**
 *
 * @author danglph
 */
public class daoSysConfig {
    
    private static volatile SessionFactory sf;// = HibernateUtil.getSessionFactory();
    private static final Logger logger = Logger.getLogger(daoSysConfig.class);
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        insert("ÈG", "?","?");
    }
    
    public static boolean insert(String key, String... values) {
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        boolean isSuccess = false;
//        int numbSuccess = 0;
        try {
            s = sf.openSession();
            s.beginTransaction();
            SysConfig l = (SysConfig)s.createQuery("From SysConfig WHERE keyCfg =:keyCfg")
                    .setParameter("keyCfg", key).uniqueResult();
            if(l == null){
//                logger.debug("Insert: " + key);
                l = new SysConfig(key);
                l.setValue1(values[0]);
                l.setValue2(values[1]);
                s.save(l);
                isSuccess = true;
            }else{
                logger.debug("Exist: " + key);
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
    
    
    public static void update(SysConfig sysConfig) {
        
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            s.beginTransaction();
            s.saveOrUpdate(sysConfig);
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

    public static SysConfig getByKey(String key) {
        SysConfig result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            result = (SysConfig)s.createQuery("From SysConfig WHERE keyCfg =:keyCfg")
                    .setParameter("keyCfg", key).uniqueResult();
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
}
