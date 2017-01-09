package DAO.impl;

import DAO.BaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zky on 2017/1/9.
 */
@Component
public class BaseDAOHibernate4<T> implements BaseDAO<T>{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T get(Class<T> entityClazz, Serializable id) {
        return (T) getSessionFactory().openSession().get(entityClazz, id);
    }

    public Serializable save(T entity) {
        return getSessionFactory().getCurrentSession().save(entity);
    }

    public void update(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(session.merge(entity));
        transaction.commit();
        session.close();
    }

    public void delete(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.merge(entity));
        transaction.commit();
        session.close();
    }

    public void delete(Class<T> entityClazz, Serializable id) {
        getSessionFactory().getCurrentSession().
                createQuery("delete " + entityClazz.getSimpleName() + " en where en.id = ?1").
                setParameter(1, id).executeUpdate();
    }

    public List<T> findAll(Class<T> entityClazz) {
        return find("select en from " + entityClazz.getSimpleName() + " en");
    }

    public long findCount(Class<T> entityClazz) {
        List<?> list = find("select count(*) from " + entityClazz.getSimpleName());
        if(list != null && list.size() == 1)
            return (Long) list.get(0);
        else
            return 0;
    }

    protected List<T> find(String hql){
        return (List<T>) getSessionFactory().getCurrentSession().createQuery(hql).list();
    }
}
