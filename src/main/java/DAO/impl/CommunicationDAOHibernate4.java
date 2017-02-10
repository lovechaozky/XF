package DAO.impl;

import DAO.CommunicationDAO;
import DAO.querypage.Page;
import domain.Communication;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zky on 2017/2/11.
 */
@Component("CommunicationDAOHibernate4")
public class CommunicationDAOHibernate4 extends BaseDAOHibernate4<Communication> implements CommunicationDAO{

    public int getPageCount(int everyPage) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select count(*) from Communication");
        int rowCount = (Integer)query.uniqueResult();
        session.close();
        if(rowCount == 0)
            return 0;
        else{
            if(rowCount % everyPage == 0)
                return rowCount / everyPage;
            else
                return rowCount / everyPage + 1;
        }
    }

    public void add(Communication communication) {
        super.save(communication);
    }

    public void update(Communication communication){
        super.update(communication);
    }

    public void delete(Communication communication){
        super.delete(communication);
    }

    public void delete(int id) {
        super.delete(Communication.class, id);
    }

    public Communication getById(int id) {
        return super.get(Communication.class, id);
    }

    public List<Communication> getByTime(String time, Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Communication where time=?");
        query.setString(0, time);
        query.setMaxResults(page.getEveryPage());
        query.setFirstResult(page.getBeginIndex());
        List<Communication> communications = query.list();
        session.close();
        return communications;
    }

    public List<Communication> getByPerson(String person, Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Communication where person=?");
        query.setString(0, person);
        query.setMaxResults(page.getEveryPage());
        query.setFirstResult(page.getBeginIndex());
        List<Communication> communications = query.list();
        session.close();
        return communications;
    }

    public List<Communication> getAll(Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Communication");
        query.setMaxResults(page.getEveryPage());
        query.setFirstResult(page.getBeginIndex());
        List<Communication> communications = query.list();
        session.close();
        return communications;
    }
}
