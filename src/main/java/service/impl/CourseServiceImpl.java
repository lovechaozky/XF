package service.impl;

import DAO.CourseDAO;
import DAO.querypage.Page;
import domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CourseService;

import java.util.List;

/**
 * Created by zky on 2017/2/8.
 */
@Service("CourseService")
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseDAO courseDAO;

    public void addCourse(Course course) {
        courseDAO.add(course);
    }

    public void updateCourse(Course course) {
        Course oldCourse = courseDAO.getById(course.getId());
        if(course.getCourse() != null && !"".equals(course.getCourse()))
            oldCourse.setCourse(course.getCourse());
        if(course.getPlace() != null && !"".equals(course.getPlace()))
            oldCourse.setPlace(course.getPlace());
        if(course.getTime() != null && !"".equals(course.getTime()))
            oldCourse.setTime(course.getTime());
        courseDAO.update(oldCourse);
    }

    public int getPageCount(int everyPage) {
        return courseDAO.getPageCount(everyPage);
    }

    public List<Course> getCourseByTime(String time, Page page) {
        return courseDAO.getByTime(time, page);
    }
}
