package DAO.impl;

import DAO.CourseDAO;
import DAO.querypage.Page;
import domain.Course;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/2/8.
 */
@Component("CourseDAOHibernate4")
public class CourseDAOHibernate4 extends BaseDAOHibernate4<Course> implements CourseDAO{

    public int getPageCount(int everyPage) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select count(*) from Course");
        int rowCount = (Integer)query.uniqueResult();
        session.close();
        if(rowCount == 0)
            return 0;
        else{
            if(rowCount % everyPage == 0)
                return rowCount / everyPage;
            else
                return rowCount / everyPage + 1;
        }
    }

    public void add(Course course) {
        super.save(course);
    }

    public void update(Course course){
        super.update(course);
    }

    public Course getById(int id) {
        return super.get(Course.class, id);
    }

    public List<Course> getByTime(String time, Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Course where time=?");
        query.setString(0, time);
        query.setMaxResults(page.getEveryPage());
        query.setFirstResult(page.getBeginIndex());
        List<Course> courses = query.list();
        session.close();
        return courses;
    }
}
