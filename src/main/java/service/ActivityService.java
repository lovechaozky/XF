package service;

import DAO.querypage.Page;
import domain.Activity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by XiaoXiaoBing on 2017/1/14.
 */
@Service()
@Transactional
public interface ActivityService {
    List<Activity> list();
    List<Activity> list(Page page);
    int getPageCount(int i);
    Activity get(int id);
    void add(Activity activity);
    void delete(int id);
    void delete(Activity activity);
    void update(Activity activity);
}
