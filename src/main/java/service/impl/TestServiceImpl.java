package service.impl;

import DAO.TestDAO;
import domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TestService;

/**
 * Created by zky on 2017/1/9.
 */
@Service("TestService")
public class TestServiceImpl implements TestService{

    @Autowired
    TestDAO testDAO;

    public void add(Test test) {
        testDAO.add(test);
    }

    public void update(Test test) {
        Test oldTest = testDAO.getById(test.getId());
        oldTest.setTest1(test.getTest1());
        testDAO.update(oldTest);
    }

    public void delete(Test test) {
        testDAO.delete(test);
    }

    public Test getById(int id) {
        return testDAO.getById(id);
    }
}
