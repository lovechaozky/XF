package DAO.impl;

import DAO.TestDAO;
import domain.Test;
import org.springframework.stereotype.Component;

/**
 * Created by zky on 2017/1/9.
 */
@Component("TestDAOHibernate4")
public class TestDAOHibernate4 extends BaseDAOHibernate4<Test> implements TestDAO{

    public void add(Test test) {
        super.save(test);
    }

    public Test getById(int id) {
        return super.get(Test.class, id);
    }

    public void update(Test test){
        super.update(test);
    }

    public void delete(Test test){
        super.delete(test);
    }
}
