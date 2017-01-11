package DAO.impl;

import DAO.AdminDAO;
import domain.Admin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/1/12.
 */
@Component("AdminDAOHibernate4")
public class AdminDAOHibernate4 extends BaseDAOHibernate4<Admin> implements AdminDAO{

    public Admin getByAccountAndPassword(String account, String password) {
        Admin admin = null;
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from Admin where account=? and password=?");
        query.setString(0, account);
        query.setString(1, password);
        List<Admin> admins = query.list();
        if(admins.size() > 0)
            admin = admins.get(0);
        session.close();
        return admin;
    }
}
