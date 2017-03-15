package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ognl.accessor.ObjectAccessor;
import domain.Admin;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zky on 2017/1/12.
 */
@Namespace("")
@ParentPackage("struts-default")
public class AdminAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, SessionAware{

    HttpServletResponse response;
    HttpServletRequest request;
    Map<String, Object> session;
    AdminService adminService;
    ObjectMapper objectMapper;

    public AdminAction(){
        super();
        objectMapper = new ObjectMapper();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        adminService = (AdminService) applicationContext.getBean("AdminService");
    }

    @Action(value = "adminLogin")
    public void adminLogin()throws Exception{
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Admin admin = adminService.getAdminByAccountAndPassword(account, password);
//        System.out.println(admin.toString());
        Map map = new HashMap();
        if(admin == null){
            map.put("adminLoginState", "fail");
        }
        else{
            map.put("adminLoginState", "success");
            session.put("admin", admin);
        }
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
