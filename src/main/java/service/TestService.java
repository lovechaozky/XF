package service;

import domain.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zky on 2017/1/9.
 */
@Service()
@Transactional
public interface TestService {

    public void add(Test test);

    public void update(Test test);

    public void delete(Test test);

    public Test getById(int id);

}
