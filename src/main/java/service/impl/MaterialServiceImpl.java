package service.impl;

import DAO.MaterialDAO;
import DAO.querypage.Page;
import domain.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.MaterialService;

import java.util.List;

/**
 * Created by zky on 2017/1/22.
 */
@Service("MaterialService")
public class MaterialServiceImpl implements MaterialService{

    @Autowired
    MaterialDAO materialDAO;

    public int getPageCount(int everyPage) {
        return materialDAO.getPageCount(everyPage);
    }

    public void addMaterial(Material material) {
        materialDAO.add(material);
    }

    public void deleteMaterial(Material material) {
        materialDAO.add(material);
    }

    public List<Material> getAllMaterial(Page page) {
        return materialDAO.getAll(page);
    }

    public Material getMaterialById(int id) {
        return materialDAO.getById(id);
    }
}
