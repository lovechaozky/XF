package DAO.impl;

import DAO.CourseDAO;
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

    public void add(Course course) {
        super.save(course);
    }

    public void update(Course course){
        super.update(course);
    }

    public Course getById(int id) {
        return super.get(Course.class, id);
    }

    public List<Course> getByTime(String time) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Course where time=?");
        query.setString(0, time);
        List<Course> courses = query.list();
        session.close();
        return courses;
    }
}
