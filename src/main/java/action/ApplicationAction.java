package action;

import DAO.querypage.Page;
import DAO.querypage.PageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import domain.Application;
import domain.Person;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zky on 2017/2/7.
 */
@Namespace("")
@ParentPackage("struts-default")
public class ApplicationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,SessionAware{

    HttpServletRequest request;
    HttpServletResponse response;
    Map session;
    ApplicationService applicationService;
    ObjectMapper objectMapper;

    public ApplicationAction(){
        super();
        objectMapper = new ObjectMapper();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationService = (ApplicationService) applicationContext.getBean("ApplicationService");
    }

    @Action(value = "addApplication")
    public void addApplication()throws Exception{
        String person = request.getParameter("person");
        String type = request.getParameter("type");
        String detail = request.getParameter("detail");
        String time = request.getParameter("time");
        if(detail == null)
            detail = "";
        String state = "申请中";
        Application application = new Application();
        application.setState(state);
        application.setDetail(detail);
        application.setPerson(person);
        application.setTime(time);
        application.setType(type);
        applicationService.addApplication(application);
        Map map = new HashMap();
        map.put("application", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "updateApplication")
    public void updateApplication()throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        String detail = request.getParameter("detail");
        String state = "已阅";
        Application application = new Application();
        application.setId(id);
        application.setDetail(detail);
        application.setState(state);
        applicationService.updateApplication(application);
        Map map = new HashMap();
        map.put("updateApplication", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "getApplicationByPerson")
    public void getApplicationByPerson()throws Exception{
        String person = request.getParameter("person");
        int requestPage = Integer.parseInt(request.getParameter("requestPage"));
        int everyPage = 10;
        int totalPage = applicationService.getPageCount(everyPage);
        Page page = PageUtils.createPage(everyPage, totalPage, requestPage);
        List<Application> applications = applicationService.getApplicationByPerson(person, page);
        Map map = new HashMap();
        map.put("pageCount",totalPage);
        map.put("applications", applications);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "getApplicationByState")
    public void getApplicationByState()throws Exception{
        int requestPage = Integer.parseInt(request.getParameter("requestPage"));
        int everyPage = 10;
        int totalPage = applicationService.getPageCount(everyPage);
        Page page = PageUtils.createPage(everyPage, totalPage, requestPage);
        List<Application> applications = applicationService.getApplicationByState("申请中", page);
        Map map = new HashMap();
        map.put("pageCount",totalPage);
        map.put("applications", applications);
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
