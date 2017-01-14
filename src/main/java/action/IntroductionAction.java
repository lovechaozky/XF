package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Introduction;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.IntroductionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by zky on 2017/1/12.
 */
@Namespace("")
@ParentPackage("struts-default")
public class IntroductionAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, SessionAware{

    HttpServletRequest request;
    HttpServletResponse response;
    Map session;
    IntroductionService introductionService;

    public IntroductionAction(){
        super();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        introductionService = (IntroductionService) applicationContext.getBean("IntroductionService");
    }

    @Action(value = "updateIntroduction")
    public void updateIntroduction()throws Exception{
        String introduction = request.getParameter("introduction");
        String constitution = request.getParameter("constitution");
        String rule = request.getParameter("rule");
        Introduction introduction1 = introductionService.get();
        if(introduction1 == null){
            introduction1 = new Introduction();
            introduction1.setConstitution(constitution);
            introduction1.setIntroduction(introduction);
            introduction1.setRule(rule);
            introductionService.add(introduction1);
        }
        else{
            introduction1.setConstitution(constitution);
            introduction1.setIntroduction(introduction);
            introduction1.setRule(rule);
            introductionService.update(introduction1);
        }
//        System.out.println(introduction1.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("updateIntroductionState", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "getIntroduction")
    public void getIntroduction()throws Exception{
        Introduction introduction = introductionService.get();
//        System.out.println(introduction.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("introduction", introduction);
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
