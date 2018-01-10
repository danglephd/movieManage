/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.vn.movieviewer.database.HibernateUtil;
import org.vn.movieviewer.dto.SmtLearning;

/**
 *
 * @author danglph
 */
public class daoSmrtLearning {

    private static volatile SessionFactory sf;// = HibernateUtil.getSessionFactory();
    private static final Logger logger = Logger.getLogger(daoSmrtLearning.class);

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

    }

    public static boolean insert(String action, String... arrValue) {
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        boolean isSuccess = false;
//        int numbSuccess = 0;
        try {
            s = sf.openSession();
            s.beginTransaction();
            String queryStr = "From SmtLearning WHERE smtAction =:smtAction and smtValue1 =:smtValue1";
//             and smtValue2 =:smtValue2
            Query query = s.createQuery(queryStr);
            query.setParameter("smtAction", action);
            
            switch (arrValue.length){
                case 1:
                    queryStr = "From SmtLearning WHERE smtAction =:smtAction and smtValue1 =:smtValue1";
                    query = s.createQuery(queryStr);
                    query.setParameter("smtAction", action);
                    query.setParameter("smtValue1", arrValue[0]);                                          
                    break;
                case 2:
                    queryStr = "From SmtLearning WHERE smtAction =:smtAction and smtValue1 =:smtValue1 and smtValue2 =:smtValue2";
                    query = s.createQuery(queryStr);
                    query.setParameter("smtAction", action);
                    query.setParameter("smtValue1", arrValue[0]);                                               
                    query.setParameter("smtValue2", arrValue[1]);                                        
                    break;
            }
            SmtLearning l = (SmtLearning) query.uniqueResult();
            
            if (l == null) {
                logger.debug("Insert: " + action);
                l = new SmtLearning();
                l.setSmtAction(action);
                for (int i = 0; i < arrValue.length; i++) {
//                    String string = arrValue[i];
                    switch (i){
                        case 0:
                            l.setSmtValue1(arrValue[i]);                                            
                            break;
                        case 1:
                            l.setSmtValue2(arrValue[i]);                                            
                            break;
                    }
                }
                s.save(l);
                isSuccess = true;
            } else {
                logger.debug("Exist: " + action);
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

    public static List<SmtLearning> getByAction(String action) {
        List<SmtLearning> result = null;
        sf = HibernateUtil.getSessionFactory();
        org.hibernate.Session s = null;
        try {
            s = sf.openSession();
            result = (List<SmtLearning>) s.createQuery("from SmtLearning where smtAction =:smtAction")
                    .setParameter("smtAction", action)
                    .list();
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
