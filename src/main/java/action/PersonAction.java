package action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
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
import service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zky on 2017/1/15.
 */
@Namespace("")
@ParentPackage("struts-default")
public class PersonAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware{

    HttpServletRequest request;
    HttpServletResponse response;
    Map session;
    PersonService personService;
    ObjectMapper objectMapper;

    public PersonAction(){
        super();
        objectMapper = new ObjectMapper();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        personService = (PersonService) applicationContext.getBean("PersonService");
    }

    @Action(value = "isUsernameExist")
    public void isUsernameExist()throws Exception{
        String username = request.getParameter("username");
        Person person = personService.getPersonByUsername(username);
//        System.out.println(person.toString());
        Map map = new HashMap();
        if(person == null)
            map.put("isExist", "no");
        else
            map.put("isExist", "yes");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "register")
    public void register()throws Exception{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String classes = request.getParameter("classes");
        String number = request.getParameter("number");
        String phone = request.getParameter("phone");
        String qq = request.getParameter("qq");
        Person person = new Person();
        person.setPassword(password);
        person.setPhone(phone);
        person.setNumber(number);
        person.setQq(qq);
        person.setClasses(classes);
        person.setUsername(username);
        personService.addPerson(person);
//        System.out.println(person.toString());
        Map map = new HashMap();
        map.put("register", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "login")
    public void login()throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Person person = personService.getPersonByUsernameAndPassword(username, password);
        Map map = new HashMap();
        if (person == null){
            map.put("loginState", "fail");
//            System.out.println("fail");
        }
        else{
            map.put("loginState", "success");
//            System.out.println(person.toString());
            session.put("person", person);
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

    @Action(value = "updatePersonInfo")
    public void updatePersonInfo()throws Exception{
        String classes = request.getParameter("classes");
        String number = request.getParameter("number");
        String phone = request.getParameter("phone");
        String qq = request.getParameter("qq");
        Person oldPerson = (Person) session.get("person");
//        Person oldPerson = personService.getPersonById(1);
        oldPerson.setPhone(phone);
        oldPerson.setNumber(number);
        oldPerson.setQq(qq);
        oldPerson.setClasses(classes);
        Person newPerson = personService.updatePerson(oldPerson);
//        System.out.println(newPerson.toString());
        session.put("person", newPerson);
        Map map = new HashMap();
        map.put("updatePersonInfo", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "updatePersonPassword")
    public void updatePersonPassword()throws Exception{
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        Person oldPerson = (Person) session.get("person");
//        Person oldPerson = personService.getPersonById(1);
        Map map = new HashMap();
        if(personService.getPersonByUsernameAndPassword(oldPerson.getUsername(), oldPassword) == null){
            map.put("updatePersonPassword", "fail");
//            System.out.println("fail");
        }
        else{
            map.put("updatePersonPassword", "success");
            oldPerson.setPassword(password);
            Person newPerson = personService.updatePassword(oldPerson);
//            System.out.println(newPerson.toString());
            session.put("person", newPerson);
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

    @Action(value = "getPersonByUsername")
    public void getPersonByUsername()throws Exception{
        String username = request.getParameter("username");
        Person person = personService.getPersonByUsername(username);
        Map map = new HashMap();
        if(person == null){
            map.put("briefPersonInfo", "fail");
//            System.out.println("fail");
        }
        else{
            map.put("briefPersonInfo", "success");
            person.setPassword("");
//            System.out.println(person.toString());
            session.put("briefPersonInfo", person);
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

    @Action(value = "getPersonById")
    public void getPersonById()throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        Person person = personService.getPersonById(id);
        Map map = new HashMap();
        if(person == null){
            map.put("briefPersonInfo", "fail");
//            System.out.println("fail");
        }
        else{
            map.put("briefPersonInfo", "success");
            person.setPassword("");
//            System.out.println(person.toString());
            session.put("briefPersonInfo", person);
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
