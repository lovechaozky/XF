package DAO.impl;

import DAO.ApplicationDAO;
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

    public List<Application> getByState(String state) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Application where state=?");
        query.setString(0, state);
        List<Application> applications = query.list();
        session.close();
        return applications;
    }

    public List<Application> getByPerson(String person) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Application where person=?");
        query.setString(0, person);
        List<Application> applications = query.list();
        session.close();
        return applications;
    }

    public Application getById(int id){
        return super.get(Application.class, id);
    }
}
