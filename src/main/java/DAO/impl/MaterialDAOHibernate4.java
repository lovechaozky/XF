package DAO.impl;

import DAO.BaseDAO;
import DAO.MaterialDAO;
import domain.Material;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/1/22.
 */
@Component("MaterialDAOHibernate4")
public class MaterialDAOHibernate4 extends BaseDAOHibernate4<Material> implements MaterialDAO{

    public void add(Material material) {
        super.save(material);
    }

    public void delete(Material material){
        super.delete(material);
    }

    public List<Material> getAll() {
        return super.findAll(Material.class);
    }

    public Material getById(int id) {
        Material material = null;
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Material where id=?");
        query.setInteger(0, id);
        List<Material> materials = query.list();
        if(materials.size() > 0)
            material = materials.get(0);
        session.close();
        return material;
    }
}
