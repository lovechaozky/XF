package action;

import DAO.querypage.Page;
import DAO.querypage.PageUtils;
import com.opensymphony.xwork2.ActionSupport;
import domain.CounselingRoom;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CounselingRoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by zky on 2017/2/10.
 */
@Namespace("")
@ParentPackage("struts-default")
public class CounselingRoomAction extends ActionSupport implements ServletResponseAware,ServletRequestAware,SessionAware{

    HttpServletRequest request;
    HttpServletResponse response;
    Map session;
    CounselingRoomService counselingRoomService;

    public CounselingRoomAction(){
        super();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        counselingRoomService = (CounselingRoomService) applicationContext.getBean("CounselingRoomService");
    }

    @Action(value = "addCounselingRoom")
    public void addCounselingRoom()throws Exception{
        String place = request.getParameter("place");
        String time = request.getParameter("time");
        CounselingRoom counselingRoom = new CounselingRoom();
        counselingRoom.setPlace(place);
        counselingRoom.setTime(time);
        counselingRoomService.addCounselingRoom(counselingRoom);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addCounselingRoom", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "updateCounselingRoom")
    public void updateCounselingRoom()throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        String place = request.getParameter("place");
        String time = request.getParameter("time");
        CounselingRoom counselingRoom = new CounselingRoom();
        counselingRoom.setPlace(place);
        counselingRoom.setTime(time);
        counselingRoomService.updateCounselingRoom(counselingRoom);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("updateCounselingRoom", "success");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "getCounselingRoomByTime")
    public void getCounselingRoomByTime()throws Exception{
        String time = request.getParameter("time");
        int requestPage = Integer.parseInt(request.getParameter("requestPage"));
        int everyPage = 10;
        int totalPage = counselingRoomService.getPageCount(everyPage);
        Page page = PageUtils.createPage(everyPage, totalPage, requestPage);
        List<CounselingRoom> counselingRooms = counselingRoomService.getCounselingRoomByTime(time, page);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageCount",totalPage);
        jsonObject.put("counselingRooms", counselingRooms);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "getCounselingRoomByPlace")
    public void getCounselingRoomByPlace()throws Exception{
        String place = request.getParameter("place");
        int requestPage = Integer.parseInt(request.getParameter("requestPage"));
        int everyPage = 10;
        int totalPage = counselingRoomService.getPageCount(everyPage);
        Page page = PageUtils.createPage(everyPage, totalPage, requestPage);
        List<CounselingRoom> counselingRooms = counselingRoomService.getCounselingRoomByPlace(place, page);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageCount",totalPage);
        jsonObject.put("counselingRooms", counselingRooms);
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
