package DAO.impl;

import DAO.ActivityDAO;
import domain.Activity;
import org.springframework.stereotype.Component;

/**
 * Created by XiaoXiaoBing on 2017/1/14.
 */
@Component("ActivityDAOHibernate4")
public class ActivityDAOHibernate4 extends BaseDAOHibernate4<Activity> implements ActivityDAO {
}
