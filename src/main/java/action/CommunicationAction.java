package action;

import DAO.querypage.Page;
import DAO.querypage.PageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import domain.Communication;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CommunicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zky on 2017/2/11.
 */
@Namespace("")
@ParentPackage("struts-default")
public class CommunicationAction extends ActionSupport implements ServletResponseAware,ServletRequestAware,SessionAware{

    HttpServletRequest request;
    HttpServletResponse response;
    Map session;
    CommunicationService communicationService;
    ObjectMapper objectMapper;

    public CommunicationAction(){
        super();
        objectMapper = new ObjectMapper();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        communicationService = (CommunicationService) applicationContext.getBean("CommunicationService");
    }

    @Action(value = "addCommunication")
    public void addCommunication()throws Exception{
        String person = request.getParameter("person");
        String time = request.getParameter("time");
        String message = request.getParameter("message");
        Communication communication = new Communication();
        communication.setMessage(message);
        communication.setTime(time);
        communication.setPerson(person);
        communicationService.addCommunication(communication);
        Map map = new HashMap();
        map.put("addCommunication", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "updateCommunication")
    public void updateCommunication()throws Exception{
        String message = request.getParameter("message");
        int id = Integer.parseInt(request.getParameter("id"));
        Communication communication = new Communication();
        communication.setMessage(message);
        communication.setId(id);
        communicationService.updateCommunication(communication);
        Map map = new HashMap();
        map.put("updateCommunication", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "deleteCommunication")
    public void deleteCommunication()throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        communicationService.deleteById(id);
        Map map = new HashMap();
        map.put("deleteCommunication", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "getCommunicationByTime")
    public void getCommunicationByTime()throws Exception{
        String time = request.getParameter("time");
        int requestPage = Integer.parseInt(request.getParameter("requestPage"));
        int everyPage = 10;
        int totalPage = communicationService.getPageCount(everyPage);
        Page page = PageUtils.createPage(everyPage, totalPage, requestPage);
        List<Communication> communications = communicationService.getCommunicationByTime(time, page);
        Map map = new HashMap();
        map.put("pageCount",totalPage);
        map.put("communications", communications);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "getCommunicationByPerson")
    public void getCommunicationByPerson()throws Exception{
        String person = request.getParameter("person");
        int requestPage = Integer.parseInt(request.getParameter("requestPage"));
        int everyPage = 10;
        int totalPage = communicationService.getPageCount(everyPage);
        Page page = PageUtils.createPage(everyPage, totalPage, requestPage);
        List<Communication> communications = communicationService.getCommunicationByPerson(person, page);
        Map map = new HashMap();
        map.put("pageCount",totalPage);
        map.put("communications", communications);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        Writer writer = new StringWriter();
        objectMapper.writeValue(writer, map);
        printWriter.write(writer.toString());
        writer.close();
        printWriter.close();
    }

    @Action(value = "getAllCommunication")
    public void getAllCommunication()throws Exception{
        int requestPage = Integer.parseInt(request.getParameter("requestPage"));
        int everyPage = 10;
        int totalPage = communicationService.getPageCount(everyPage);
        Page page = PageUtils.createPage(everyPage, totalPage, requestPage);
        List<Communication> communications = communicationService.getAllCommunication(page);
        Map map = new HashMap();
        map.put("pageCount",totalPage);
        map.put("communications", communications);
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
