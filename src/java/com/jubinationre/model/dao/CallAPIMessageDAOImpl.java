/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubinationre.model.dao;


import com.jubinationre.model.pojo.CallAPIMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Welcome
 */
@Repository
public class CallAPIMessageDAOImpl<T> implements java.io.Serializable, GenericDAO {
private Session session=null;
    @Autowired
    private SessionFactory sessionFactory;

        
    public Object buildEntity(Object entity) {
        CallAPIMessage msg=(CallAPIMessage) entity;
        try{
            session = getSessionFactory().openSession();
            session.beginTransaction();
            session.save(msg);
            msg = (CallAPIMessage) session.get(CallAPIMessage.class, msg.getOrderId());
            session.getTransaction().commit();
        }
        catch(Exception e){
            session.getTransaction().rollback();
            System.out.println("Error in building CallAPIMessage and its properties at CallAPIDAO "+e);
            e.printStackTrace();
            msg=null;
        }
        finally{
            if(session.isOpen()){
                
            }
            session=null;
        }
        return (T) msg;

    }


   
  

    public Object getByProperty(Object entity, String listType) {
        List<CallAPIMessage> list = new ArrayList<CallAPIMessage>();
        switch(listType){
            case "Number":
                    String number= (String) entity;
                    try{
                      session = getSessionFactory().openSession();
                      session.beginTransaction();
                      Criteria criteria = session.createCriteria(CallAPIMessage.class, "call");
                      criteria.add(Restrictions.eq("CallTo", number));
                      list=criteria.list();
                      session.getTransaction().commit();

                    }
                    catch(Exception e){
                        session.getTransaction().rollback();
                        System.out.println("Error in building CallAPIMessage and its properties at CallAPIDAO "+e);
                        e.printStackTrace();
                    }
       
                break;
            case "OrderId":
                    Long orderId= (Long) entity;
                    try{
                        session = getSessionFactory().openSession();
                        session.beginTransaction();
                        list.add((CallAPIMessage) session.get(CallAPIMessage.class, (Long)orderId));
                        session.getTransaction().commit();
                    }
                    catch(Exception e){
                        session.getTransaction().rollback();
                        System.out.println("Error in building CallAPIMessage and its properties at CallAPIDAO "+e);
                        e.printStackTrace();


                    }
                
                break;
            case "Sid":
                    String sid= (String) entity;
                    try{
                      session = getSessionFactory().openSession();
                      session.beginTransaction();
                      Criteria criteria = session.createCriteria(CallAPIMessage.class, "call");
                      criteria.add(Restrictions.eq("Sid", sid));
                      list= criteria.list();
                      session.getTransaction().commit();

                    }
                    catch(Exception e){
                        session.getTransaction().rollback();
                        System.out.println("Error in deleting Items and its properties at ItemsDAO "+e);
                        e.printStackTrace();
                    }
                break;
                case "DateCreated":
                    String dateCreated= (String) entity;
                    try{
                      session = getSessionFactory().openSession();
                      session.beginTransaction();
                      Criteria criteria = session.createCriteria(CallAPIMessage.class, "call");
                      criteria.add(Restrictions.like("DateCreated", dateCreated,MatchMode.START));
                      list= criteria.list();
                      session.getTransaction().commit();

                    }
                    catch(Exception e){
                        session.getTransaction().rollback();
                        System.out.println("Error in deleting Items and its properties at ItemsDAO "+e);
                        e.printStackTrace();
                    }
                break;
               case "PendingOnDate":
                    String pendingOnDate= (String) entity;
                    try{
                      session = getSessionFactory().openSession();
                      session.beginTransaction();
                      Criteria criteria = session.createCriteria(CallAPIMessage.class, "call");
                       criteria.add(Restrictions.or(
                               Restrictions.and(
                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
                                    Restrictions.like("TrackStatus", "did not",MatchMode.ANYWHERE)),
                               Restrictions.and(
                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
                                    Restrictions.like("TrackStatus", "Pressed 2",MatchMode.START)),
			       Restrictions.and(
                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
                                    Restrictions.like("Status", "failed",MatchMode.START)),
			       Restrictions.and(
                                    Restrictions.like("DateCreated", pendingOnDate,MatchMode.START),
                                    Restrictions.like("Status", "no-answer",MatchMode.START))
                                    ));
                      list= criteria.list();
                      session.getTransaction().commit();

                    }
                    catch(Exception e){
                        session.getTransaction().rollback();
                        System.out.println("Error in deleting Items and its properties at ItemsDAO "+e);
                        e.printStackTrace();
                    }
                break;
            default: System.err.println("Not a valid option");
                break;
        }
    return list;
    }
    public boolean updateProperty(Object entity) {
        
        CallAPIMessage msg=(CallAPIMessage) entity;
        try{
            session = getSessionFactory().openSession();
            session.beginTransaction();
            msg=(CallAPIMessage) session.merge(msg);
            session.getTransaction().commit();
         
        }
        catch(Exception e){
            session.getTransaction().rollback();
            System.out.println("Error in updating CallAPIMessage properties at CallAPIMessageDAO "+e);
            e.printStackTrace();
        }
        finally{
            if(session.isOpen()){
                session.close();
            }
          
            return msg!=null;
        }
    }

    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Object buildEntity(Object entity, boolean coded) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean addPropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Object readPropertyList(Object entity, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
    
    public boolean updatePropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean deleteEntity(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Object deletePropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    
    public Object fetchEntity(Object property) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Object buildInnerPropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Object readInnerPropertyList(Object entity, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean deleteInnerPropertyList(Object entity, Object property, String listType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int countEntities(String paramVal, Object entity, int pageNumber, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
  
    
    public Long getNumberOfRecords(String paramVal_1, String paramVal_2, int pageSize, int pageNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Long getNumberOfRecordsOnSearch(String paramVal_1, String paramVal_2, int pageSize, int pageNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public int getNumberOfRecords(String paramVal_1, String paramVal_2, int pageSize, int pageNumber, String availability) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int getNumberOfRecordsOnSearch(String paramVal_1, Object paramVal_2, int pageSize, int pageNumber, String availability) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Object fetchEntities(String paramVal_1, String paramVal_2, String paramVal_3, int pageSize, int pageNumber, String ListType, String availability) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Object readProperty(Object paramId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List fetchEntities(String paramVal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProperty(Object entity, Object paramVal, String paramType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
