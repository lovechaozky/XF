package DAO;

import DAO.querypage.Page;
import domain.Activity;

import java.util.List;

/**
 * Created by XiaoXiaoBing on 2017/1/14.
 */
public interface ActivityDAO extends BaseDAO<Activity> {
    List<Activity> listActivities(Page page);
    int getPageCount(int i);
}
