package service;

import domain.Material;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zky on 2017/1/22.
 */
@Service()
@Transactional
public interface MaterialService {

    public void addMaterial(Material material);

    public void deleteMaterial(Material material);

    public List<Material> getAllMaterial();

    public Material getMaterialById(int id);

}
