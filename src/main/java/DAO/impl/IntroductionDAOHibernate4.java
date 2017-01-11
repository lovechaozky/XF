package DAO.impl;

import DAO.IntroductionDAO;
import domain.Introduction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/1/12.
 */
@Component("IntroductionDAOHibernate4")
public class IntroductionDAOHibernate4 extends BaseDAOHibernate4<Introduction> implements IntroductionDAO{

    public void add(Introduction introduction) {
        super.save(introduction);
    }

    public void update(Introduction introduction){
        super.update(introduction);
    }

    public Introduction get() {
        Introduction introduction = null;
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Introduction");
        List<Introduction> introductions = query.list();
        if(introductions.size() > 0)
            introduction = introductions.get(0);
        session.close();
        return introduction;
    }
}
