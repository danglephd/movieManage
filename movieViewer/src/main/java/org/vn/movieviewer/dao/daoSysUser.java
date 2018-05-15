/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.dao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.vn.movieviewer.database.HibernateUtil;
import org.vn.movieviewer.dto.SysUser;

/**
 *
 * @author danglph
 */
public class daoSysUser {
    
    private static volatile SessionFactory sf;// = HibernateUtil.getSessionFactory();
    private static final Logger logger = Logger.getLogger(daoSysUser.class);
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

//        get();
        insert("MMMM");
    }
    
    public static SysUser getByName(String userName) {
        SysUser result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            result = (SysUser)s.createQuery("From SysUser WHERE username =:name")
                    .setParameter("name", userName).uniqueResult();
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
    
    public static synchronized List<SysUser> get() {
        List<SysUser> result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            result = (List<SysUser>) s.createQuery("from SysUser").list();
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

    public static boolean insert(String userName) {
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        boolean isSuccess = false;
//        int numbSuccess = 0;
//        try {
//            s = sf.openSession();
//            s.beginTransaction();
//            SysUser l = (SysUser)s.createQuery("From SysUser WHERE name =:name")
//                    .setParameter("name", userName).uniqueResult();
//            if(l == null){
//                logger.debug("Insert: " + userName);
//                l = new SysUser(userName);
//                s.save(l);
//                isSuccess = true;
//            }else{
//                logger.debug("Exist: " + userName);
//                isSuccess = false;
//            }
//            s.getTransaction().commit();
//            s.clear();
//            s.close();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            logger.error(e.getCause());
//            HibernateUtil.closeSessionFactory();
//        }
        return isSuccess;
    }


    public static void update(SysUser genreSelected) {
        
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

    public static String encriptPWS(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            
            byte byteData[] = md.digest();
            
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            
//            System.out.println("Digest(in hex format):: " + sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }
}
