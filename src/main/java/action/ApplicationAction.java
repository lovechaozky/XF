package action;

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

    public ApplicationAction(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationService = (ApplicationService) applicationContext.getBean("ApplicationService");
    }

    @Action(value = "addApplication")
    public void addApplication()throws Exception{
        String person = request.getParameter("person");
        String type = request.getParameter("type");
        String detail = request.getParameter("detail");
        String time = request.getParameter("time");
        String state = "申请中";
        Application application = new Application();
        application.setState(state);
        application.setDetail(detail);
        application.setPerson(person);
        application.setTime(time);
        application.setType(type);
        applicationService.addApplication(application);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("application", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("updateApplication", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "getApplicationByPerson")
    public void getApplicationByPerson()throws Exception{
        String person = request.getParameter("person");
        List<Application> applications = applicationService.getApplicationByPerson(person);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("applications", applications);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "getApplicationByState")
    public void getApplicationByState()throws Exception{
        List<Application> applications = applicationService.getApplicationByState("申请中");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("applications", applications);
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
