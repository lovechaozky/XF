package DAO;

import domain.Test;
import org.springframework.stereotype.Component;

/**
 * Created by zky on 2017/1/9.
 */
@Component
public interface TestDAO extends BaseDAO<Test>{

    public void add(Test test);

    public void update(Test test);

    public void delete(Test test);

    public Test getById(int id);

}
