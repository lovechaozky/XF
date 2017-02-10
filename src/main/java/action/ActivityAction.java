package action;

import DAO.querypage.Page;
import DAO.querypage.PageUtils;
import com.opensymphony.xwork2.ActionSupport;
import domain.Activity;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ActivityService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by XiaoXiaoBing on 2017/1/14.
 */
@Namespace("")
@ParentPackage("struts-default")
public class ActivityAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, SessionAware {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map session;
    ActivityService activityService;

    public ActivityAction(){
        super();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        activityService = (ActivityService) applicationContext.getBean("ActivityService");
    }
    /**
     * put the list of activities by the question page
     */
    @Action("listActivities")
    public void listActivities(){
        int requestPage = Integer.valueOf(request.getParameter("requestPage"));
        int everyPage = 10;
        int totalPage = activityService.getPageCount(everyPage);
        Page page = PageUtils.createPage(everyPage,totalPage,requestPage);
        List<Activity> list = activityService.list(page);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageCount",totalPage);
        jsonObject.put("activities", list);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(jsonObject.toString());
            printWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * put the activity by request id
     */
    @Action("getActivity")
    public void getActivity(){
        JSONObject jsonObject = new JSONObject();
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            Activity activity = activityService.get(id);
            String contentAddress = activity.getAddress();
            InputStream fis = new FileInputStream(contentAddress);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis,"utf-8"));
            StringBuilder sb = new StringBuilder();
            String str = null;
            while ((str=br.readLine())!=null){
                sb.append(str);
            }
            String content = sb.toString();
            jsonObject.put("activity", activity);
            jsonObject.put("content",content);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(jsonObject.toString());
            printWriter.close();
        }catch (IOException e){
            e.printStackTrace();
            jsonObject.put("error","can not find the content file");
        }
    }

    /**
     * add a activity to database
     */
    @Action("addActivity")
    public void addActivity(){
        String title = request.getParameter("title");
        Date date = new Date(Long.valueOf(request.getParameter("date")));
        String address = request.getParameter("address");
        Activity activity = new Activity(title,date,address);
        activityService.add(activity);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("addActivity", "success");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(jsonObject.toString());
            printWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * update the information of a activity
     */
    @Action("updateActivity")
    public void updateActivity(){
        int id = Integer.valueOf(request.getParameter("id"));
        String title = request.getParameter("title");
        Date date = new Date(Long.valueOf(request.getParameter("date")));
        String address = request.getParameter("address");
        Activity activity = new Activity(title,date,address);
        activity.setId(id);
        activityService.update(activity);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("updateActivity", "success");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(jsonObject.toString());
            printWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * delete a activity by request id
     */
    @Action("deleteActivity")
    public void deleteActivity(){
        int id = Integer.valueOf(request.getParameter("id"));
        Activity activity = activityService.get(id);
        File file = new File(activity.getAddress());
        if(!file.delete())
            return;
        activityService.delete(id);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("deleteActivity", "success");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(jsonObject.toString());
            printWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
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
