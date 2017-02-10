package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Course;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CourseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by zky on 2017/2/8.
 */
@Namespace("")
@ParentPackage("struts-default")
public class CourseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,SessionAware{

    HttpServletRequest request;
    HttpServletResponse response;
    Map session;
    CourseService courseService;

    public CourseAction(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        courseService = (CourseService) applicationContext.getBean("CourseService");
    }

    @Action(value = "addCourse")
    public void addCourse()throws Exception{
        String time = request.getParameter("time");
        String place = request.getParameter("place");
        String courseName = request.getParameter("course");
        Course course = new Course();
        course.setCourse(courseName);
        course.setTime(time);
        course.setPlace(place);
        courseService.addCourse(course);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addCourse", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "updateCourse")
    public void updateCourse()throws Exception{
        String time = request.getParameter("time");
        String place = request.getParameter("place");
        String courseName = request.getParameter("course");
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = new Course();
        course.setPlace(place);
        course.setCourse(courseName);
        course.setTime(time);
        course.setId(id);
        courseService.updateCourse(course);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("updateCourse", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "getCourseByTime")
    public void getCourseByTime()throws Exception{
        String time = request.getParameter("time");
        List<Course> courses = courseService.getCourseByTime(time);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courses", courses);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
