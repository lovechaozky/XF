package DAO;

import DAO.querypage.Page;
import domain.Application;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/2/7.
 */
@Component
public interface ApplicationDAO extends BaseDAO<Application>{

    public int getPageCount(int everyPage);

    public void add(Application application);

    public void update(Application application);

    public List<Application> getByState(String state, Page page);

    public List<Application> getByPerson(String person, Page page);

    public Application getById(int id);

}
