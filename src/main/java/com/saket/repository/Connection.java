package com.saket.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saket.model.Album;
import com.saket.util.HibernateUtil;

import static com.saket.util.HibernateUtil.*;

import java.util.List;
import java.util.stream.Collectors;

public class Connection<T>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean save(T obj){
        boolean isSuccess = true;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try{
            session.persist(obj);
            t.commit();
        } catch (Exception e){
            isSuccess = false;
            if(t != null) t.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The item %s was inserted into the table", obj.toString()));
        return isSuccess;
    }

    public boolean update(T obj) {
        boolean isSuccess = true;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try{
            session.saveOrUpdate(obj);
            t.commit();
        } catch (Exception e){
            isSuccess = false;
            if(t != null) t.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The item %s was updated", obj.toString()));
        return isSuccess;
    }

    public boolean delete(T obj){
        boolean isSuccess = true;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        try{
            session.delete(obj);
            t.commit();
        } catch (Exception e){
            isSuccess = false;
            if(t != null) t.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The item %s was deleted", obj.toString()));
        return isSuccess;
    }

    public List<T> getObjectList(String TypeName) {
        String hql = "FROM " + TypeName;
        // try resources
        try (Session session = getSessionFactory().openSession()){
            Query query = session.createQuery(hql);
            return query.list();
        }
    }

    public List<T> getObjectListWithChildren(String hql){
        logger.info("INTO the method getObjectListWithChildren");
        try (Session session = getSessionFactory().openSession()) {
            Query<T> query = session.createQuery(hql);
            //return query.list();
            return query.list().stream().distinct().collect(Collectors.toList());
        }
    }

    public T getObjectById(String hql){
        logger.debug("INTO the method getObjectById");
        if (hql == null) return null;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        T object = (T) query.uniqueResult();
        t.commit();
        return object;
    }

    public T getObjectByName(String hql, String objName){
        logger.debug("INTO the method getObjectByName");
        if (hql == null) return null;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("param", objName.toLowerCase());
        T object = (T) query.uniqueResult();
        t.commit();
        return object;
    }

    public T getObjectByName(String hql, String param1, String param2){
        logger.debug("INTO the method getObjectByName");
        if (hql == null) return null;
        Session session = getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("param1", param1.toLowerCase().trim());
        query.setParameter("param2", param2);
        T object = (T) query.uniqueResult();
        t.commit();
        return object;
    }

    public List<Object[]> getCombinedObjects(String hql, String objName){
        logger.debug("INTO the method getCombinedObjects");
        if(hql == null) return null;
        try (Session session = getSessionFactory().openSession()){
            Query query = session.createQuery(hql);
            query.setParameter("param", objName.toLowerCase());
            List<Object[]> resultList = query.list();
            return resultList;
        }
    }

}
