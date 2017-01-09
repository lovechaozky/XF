package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Test;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zky on 2017/1/9.
 */
@Namespace("")
@ParentPackage("struts-default")
public class TestAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware{

    HttpServletRequest request;
    HttpServletResponse response;
    Map session;
    TestService testService;
    Test test;

    public TestAction(){
        super();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        testService = (TestService) applicationContext.getBean("TestService");
    }

    @Action(value = "addTest")
    public void addTest() throws Exception{
        String test1 = request.getParameter("text");
        System.out.println(test1);
        test = new Test();
        test.setTest1(test1);
        testService.add(test);
    }

    @Action(value = "updateTest")
    public void updateTest() throws Exception{
        String test1 = request.getParameter("text");
        System.out.println(test1);
        test = new Test();
        test.setTest1(test1);
        test.setId(1);
        testService.update(test);
    }
    @Action(value = "deleteTest")
    public void deleteTest() throws Exception{
        String test1 = request.getParameter("text");
        System.out.println(test1);
        test = new Test();
        test.setId(1);
        test.setTest1(test1);
        testService.delete(test);
    }

    @Action(value = "selectTest")
    public void selectTest() throws Exception{
        test = testService.getById(1);
        System.out.println(test.toString());
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
