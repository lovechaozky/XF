package service.impl;

import DAO.MaterialDAO;
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

    public void addMaterial(Material material) {
        materialDAO.add(material);
    }

    public void deleteMaterial(Material material) {
        materialDAO.add(material);
    }

    public List<Material> getAllMaterial() {
        return materialDAO.getAll();
    }

    public Material getMaterialById(int id) {
        return materialDAO.getById(id);
    }
}
