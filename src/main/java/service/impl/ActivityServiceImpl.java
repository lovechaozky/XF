package service.impl;

import DAO.ActivityDAO;
import DAO.querypage.Page;
import domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ActivityService;

import java.util.List;

/**
 * Created by XiaoXiaoBing on 2017/1/14.
 */
@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityDAO activityDAO;
    public List<Activity> list() {
        return activityDAO.findAll(Activity.class);
    }

    public List<Activity> list(Page page) {
        return activityDAO.listActivities(page);
    }

    public int getPageCount(int i) {
        return activityDAO.getPageCount(i);
    }

    public Activity get(int id) {
        return activityDAO.get(Activity.class,id);
    }

    public void add(Activity activity) {
        if(activity!=null)
            activityDAO.save(activity);
    }

    public void delete(int id) {
        activityDAO.delete(Activity.class,id);
    }

    public void delete(Activity activity) {
        activityDAO.delete(activity);
    }

    public void update(Activity activity) {
        Activity oldActivity = activityDAO.get(Activity.class,activity.getId());
        if(oldActivity!=null&&activity!=null){
            oldActivity.setAddress(activity.getAddress());
            oldActivity.setDate(activity.getDate());
            oldActivity.setTitle(activity.getTitle());
            activityDAO.update(oldActivity);
        }
    }
}
