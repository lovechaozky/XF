package DAO;

import domain.Application;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/2/7.
 */
@Component
public interface ApplicationDAO extends BaseDAO<Application>{

    public void add(Application application);

    public void update(Application application);

    public List<Application> getByState(String state);

    public List<Application> getByPerson(String person);

    public Application getById(int id);

}
