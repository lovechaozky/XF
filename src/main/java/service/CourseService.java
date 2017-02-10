package service;

import DAO.querypage.Page;
import domain.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zky on 2017/2/8.
 */
@Service()
@Transactional
public interface CourseService {

    public int getPageCount(int everyPage);

    public void addCourse(Course course);

    public void updateCourse(Course course);

    public List<Course> getCourseByTime(String time, Page page);

}
