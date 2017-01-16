package DAO.impl;

import DAO.PersonDAO;
import domain.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/1/15.
 */
@Component("PersonDAOHibernate4")
public class PersonDAOHibernate4 extends BaseDAOHibernate4<Person> implements PersonDAO{

    public void add(Person person) {
        super.save(person);
    }

    public void update(Person person){
        super.update(person);
    }

    public void delete(Person person){
        super.delete(person);
    }

    public Person getByUsername(String username) {
        Person person = null;
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Person where username=?");
        query.setString(0, username);
        List<Person> persons = query.list();
        if(persons.size() > 0)
            person = persons.get(0);
        session.close();
        return person;
    }

    public Person getByUsernameAndPassword(String username, String password) {
        Person person = null;
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Person where username=? and password=?");
        query.setString(0, username);
        query.setString(1, password);
        List<Person> persons = query.list();
        if(persons.size() > 0)
            person = persons.get(0);
        session.close();
        return person;
    }

    public Person getById(int id) {
        return super.get(Person.class, id);
    }

    public List<Person> getAll() {
        return super.findAll(Person.class);
    }
}
