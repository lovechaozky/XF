package DAO.impl;

import DAO.ActivityDAO;
import DAO.querypage.Page;
import domain.Activity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by XiaoXiaoBing on 2017/1/14.
 */
@Component("ActivityDAOHibernate4")
public class ActivityDAOHibernate4 extends BaseDAOHibernate4<Activity> implements ActivityDAO {
    public List<Activity> listActivities(Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select activity from Activity order by activity.date desc ");
        query.setMaxResults(page.getEveryPage());
        query.setFirstResult(page.getBeginIndex());
        return query.list();
    }

    public int getPageCount(int i) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select count(*) from Activity");
        long rowCount = (Long)query.uniqueResult();
        int rowCountInt =  Integer.valueOf(rowCount+"");
        if(rowCountInt==0){
            return 0;
        }else{
            if(rowCountInt%i==0) {
                return rowCountInt / i;
            }else{
                return rowCountInt/i+1;
            }
        }
    }
}
