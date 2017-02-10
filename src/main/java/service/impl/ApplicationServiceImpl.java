package service.impl;

import DAO.ApplicationDAO;
import DAO.querypage.Page;
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
        if(application.getDetail() != null && !"".equals(application.getDetail()))
            oldApplication.setDetail(application.getDetail());
        applicationDAO.update(oldApplication);
    }

    public int getPageCount(int everyPage) {
        return applicationDAO.getPageCount(everyPage);
    }

    public List<Application> getApplicationByState(String state, Page page) {
        return applicationDAO.getByState(state, page);
    }

    public List<Application> getApplicationByPerson(String person, Page page) {
        return applicationDAO.getByPerson(person, page);
    }
}
