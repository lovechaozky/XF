package action;

import DAO.querypage.Page;
import DAO.querypage.PageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
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
    ObjectMapper objectMapper;

    public CourseAction(){
        super();
        objectMapper = new ObjectMapper();
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
        Map map = new HashMap();
        map.put("addCourse", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
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
        Map map = new HashMap();
        map.put("updateCourse", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "getCourseByTime")
    public void getCourseByTime()throws Exception{
        String time = request.getParameter("time");
        int requestPage = Integer.parseInt(request.getParameter("requestPage"));
        int everyPage = 10;
        int totalPage = courseService.getPageCount(everyPage);
        Page page = PageUtils.createPage(everyPage, totalPage, requestPage);
        List<Course> courses = courseService.getCourseByTime(time, page);
        Map map = new HashMap();
        map.put("pageCount",totalPage);
        map.put("courses", courses);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
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
