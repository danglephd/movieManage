/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.database;

import java.io.File;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author danglph
 */
public class HibernateUtil {

    private static volatile SessionFactory sessionFactory;

    static synchronized void buildSessionFactory() {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            String pathFileHibernateConfig = System.getProperty("user.dir") + File.separator + "hibernate.cfg.xml";
            ClassLoader classLoader = HibernateUtil.class.getClassLoader();
            File hibernatePropsFile = new File(pathFileHibernateConfig);

            sessionFactory = new AnnotationConfiguration().configure(hibernatePropsFile).buildSessionFactory();

//            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            buildSessionFactory();
        }
        return sessionFactory;
    }

    public static synchronized void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }

    public static synchronized void openSessionFactory() {
        buildSessionFactory();
    }
}
