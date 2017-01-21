package DAO;

import domain.Material;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/1/22.
 */
@Component
public interface MaterialDAO extends BaseDAO<Material>{

    public void add(Material material);

    public void delete(Material material);

    public List<Material> getAll();

    public Material getById(int id);

}
