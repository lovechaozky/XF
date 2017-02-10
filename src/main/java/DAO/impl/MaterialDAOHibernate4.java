package DAO.impl;

import DAO.BaseDAO;
import DAO.MaterialDAO;
import DAO.querypage.Page;
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

    public int getPageCount(int everyPage) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select count(*) from Material");
        int rowCount = (Integer)query.uniqueResult();
        session.close();
        if(rowCount == 0)
            return 0;
        else{
            if(rowCount % everyPage == 0)
                return rowCount / everyPage;
            else
                return rowCount / everyPage + 1;
        }
    }

    public void add(Material material) {
        super.save(material);
    }

    public void delete(Material material){
        super.delete(material);
    }

    public List<Material> getAll(Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Material");
        query.setFirstResult(page.getBeginIndex());
        query.setMaxResults(page.getEveryPage());
        List<Material> materials = query.list();
        session.close();
        return materials;
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
