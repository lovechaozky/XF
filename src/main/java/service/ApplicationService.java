package service;

import domain.Application;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zky on 2017/2/7.
 */
@Service()
@Transactional
public interface ApplicationService {

    public void addApplication(Application application);

    public void updateApplication(Application application);

    public List<Application> getApplicationByState(String state);

    public List<Application> getApplicationByPerson(String person);

}
