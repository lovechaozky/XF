package DAO;

import DAO.querypage.Page;
import domain.Course;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/2/8.
 */
@Component
public interface CourseDAO extends BaseDAO<Course>{

    public int getPageCount(int everyPage);

    public void add(Course course);

    public void update(Course course);

    public Course getById(int id);

    public List<Course> getByTime(String time, Page page);

}
