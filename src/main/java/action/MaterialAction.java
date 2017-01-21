package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Material;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.MaterialService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by zky on 2017/1/22.
 */
@Namespace("")
@ParentPackage("struts-default")
public class MaterialAction extends ActionSupport implements ServletResponseAware,ServletRequestAware,SessionAware{

    HttpServletRequest request;
    HttpServletResponse response;
    Map session;
    MaterialService materialService;
    File file;

    public MaterialAction(){
        super();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        materialService = (MaterialService) applicationContext.getBean("MaterialService");
    }

    @Action(value = "addMaterial")
    public void addMaterial()throws Exception{
        String title = request.getParameter("title");
        JSONObject jsonObject = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if(file != null){
            Material material = new Material();
            material.setTitle(title);
            File directory = new File("");
            File saveFile = new File(directory, file.getName());
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            FileUtils.copyFile(file, saveFile);
            String address = saveFile.getAbsolutePath();
            material.setAddress(address);
            materialService.addMaterial(material);
            jsonObject.put("addMaterial", "success");
        }
        else{
            jsonObject.put("addMaterial", "fail");
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "deleteMaterial")
    public void deleteMaterial()throws Exception{
        int id = Integer.parseInt(request.getParameter("id"));
        Material material = materialService.getMaterialById(id);
        File file = new File(material.getAddress());
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("html/text;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deleteMaterial", "success");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    @Action(value = "getAllMaterial")
    public void getAllMaterial()throws Exception{
        List<Material> materials = materialService.getAllMaterial();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("materials", materials);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonObject.toString());
        printWriter.close();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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
