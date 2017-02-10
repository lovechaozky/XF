package DAO.impl;

import DAO.ApplicationDAO;
import DAO.querypage.Page;
import domain.Application;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/2/7.
 */
@Component("ApplicationDAOHibernate4")
public class ApplicationDAOHibernate4 extends BaseDAOHibernate4<Application> implements ApplicationDAO{

    public void add(Application application) {
        super.save(application);
    }

    public void update(Application application){
        super.update(application);
    }

    public int getPageCount(int everyPage) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select count(*) from Application");
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

    public List<Application> getByState(String state, Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Application where state=?");
        query.setString(0, state);
        query.setFirstResult(page.getBeginIndex());
        query.setMaxResults(page.getEveryPage());
        List<Application> applications = query.list();
        session.close();
        return applications;
    }

    public List<Application> getByPerson(String person, Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Application where person=?");
        query.setString(0, person);
        query.setMaxResults(page.getEveryPage());
        query.setFirstResult(page.getBeginIndex());
        List<Application> applications = query.list();
        session.close();
        return applications;
    }

    public Application getById(int id){
        return super.get(Application.class, id);
    }
}
