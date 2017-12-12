/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
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

//        getRowCount();
        updateMovie(get());
    }

    private static void updateMovie(List<MainMovie> lstMovie) {
        List<MainMovie> lstUpdateMovie = null;
        String movieName = "";
        String[] replaceStrArray = new String[]{
            //            "720p", "BluRay", "x264","X264", "SPARKS",
            //            "[rarbg]", "DRONES", "LIMITED", "AMIABLE", "WiKi"
            "1972", "1974", "1994",
            "2000", "2001", "2002", "2003", "2004",
            "2005", "2006", "2007", "2008", "2009",
            "2010", "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018", "2019",};

        for (Iterator<MainMovie> iterator = lstMovie.iterator(); iterator.hasNext();) {
            MainMovie next = iterator.next();
            movieName = next.getName();
            boolean changename = false;
            changename = false;
            for (int i = 0; i < replaceStrArray.length; i++) {
                if (movieName.contains(replaceStrArray[i])) {
//                    movieName = movieName.replace(replaceStrArray[i], "");
//                    movieName = movieName.trim();
//                    next.setName(movieName);
                    next.setReleaseYear(Integer.parseInt(replaceStrArray[i]));
                    changename = true;
                    break;
                }
            }
            if (changename) {
                if (lstUpdateMovie == null) {
                    lstUpdateMovie = new ArrayList<>();
                }
                lstUpdateMovie.add(next);
            }
        }
        update(lstUpdateMovie);
    }

    public static synchronized List<MainMovie> get(boolean isSort, String conditions, String sortStr, int start, int maxResult) {
        List<MainMovie> result = null;
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            String queryStr = "from MainMovie " + conditions;
            if (isSort) {
                queryStr += " ORDER BY " + sortStr;
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
            String squery = "SELECT m.idmainMovie FROM MainMovie m";
            result = s.createQuery(squery).list();
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
            result = ((Long) o).intValue();
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
                MainMovie l = (MainMovie) s.createQuery("From MainMovie WHERE name =:name")
                        .setParameter("name", o.getName()).uniqueResult();
                if (l == null) {
                    logger.debug("Insert: " + o.getName());
                    s.save(o);
                    numbSuccess++;
                } else {
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

    public static void update(List<MainMovie> lstUpdateMovie) {

        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            s.beginTransaction();
            for (Iterator<MainMovie> iterator = lstUpdateMovie.iterator(); iterator.hasNext();) {
                MainMovie next = iterator.next();
                next.setUpdateDate(new Date());
                s.saveOrUpdate(next);
            }
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

    public static void update(List<MainMovie> selectedMovies, String entityProperties, Map<String, Object> params) {
//        UPDATE `movies_db`.`main_movie` SET `isWatched`='1' WHERE `idmain_movie`='109';
        sf = HibernateUtil.getSessionFactory();
        Session s = null;
        try {
            s = sf.openSession();
            s.beginTransaction();

            String strQuery = "UPDATE MainMovie SET " + entityProperties + " WHERE idmain_movie in (";
            boolean isFirst = true;
            for (Iterator<MainMovie> iterator = selectedMovies.iterator(); iterator.hasNext();) {
                MainMovie next = iterator.next();
                if(isFirst){
                    strQuery += next.getIdmainMovie();
                    isFirst = false;
                }else{
                    strQuery += ", " + next.getIdmainMovie();                    
                }
            }
            strQuery += ")";

            Query query = s.createQuery(strQuery).setTimeout(3000);
            if (params != null) {
                for (Map.Entry<String, ? extends Object> param : params.entrySet()) {
                    query.setParameter(param.getKey(), param.getValue());
                }
            }

            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            query.executeUpdate();

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
