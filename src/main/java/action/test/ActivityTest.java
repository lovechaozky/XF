package action.test;

import domain.Activity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ActivityService;
import service.AdminService;

import java.util.Date;
import java.util.List;

/**
 * Created by XiaoXiaoBing on 2017/1/14.
 */
public class ActivityTest {
    public static void main(String[] args){
        ActivityTest activityTest = new ActivityTest();
        Activity activity = new Activity("abc",new Date(),"def");
        //activity.setId(3);
        //activityTest.addActivity(activity);
        //activityTest.deleteActivity(activity);
        //activity.setTitle("123");
        //activity.setId(6);
        //activityService.update(activity);
        activityTest.listActivities();
    }
    static ActivityService activityService;
    static {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        activityService = (ActivityService) applicationContext.getBean("ActivityService");
    }
    public void listActivities(){
        List<Activity> list = activityService.list();
        System.out.println(list);
    }
    public void addActivity(Activity activity){
        activityService.add(activity);
    }
    public void deleteActivity(Activity activity){
    activityService.delete(activity);
    }
    public void deleteActivity(int id){
    activityService.delete(id);
    }
    public void updateActivity(Activity activity){
    activityService.update(activity);
    }
}
