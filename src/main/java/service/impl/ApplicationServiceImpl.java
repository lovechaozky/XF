package service.impl;

import DAO.ApplicationDAO;
import domain.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ApplicationService;

import java.util.List;

/**
 * Created by zky on 2017/2/7.
 */
@Service("ApplicationService")
public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    ApplicationDAO applicationDAO;

    public void addApplication(Application application) {
        applicationDAO.add(application);
    }

    public void updateApplication(Application application) {
        Application oldApplication = applicationDAO.getById(application.getId());
        oldApplication.setState(application.getState());
        oldApplication.setDetail(application.getDetail());
        applicationDAO.update(application);
    }

    public List<Application> getApplicationByState(String state) {
        return applicationDAO.getByState(state);
    }

    public List<Application> getApplicationByPerson(String person) {
        return applicationDAO.getByPerson(person);
    }
}
