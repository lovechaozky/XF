package service.impl;

import DAO.AdminDAO;
import domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AdminService;

/**
 * Created by zky on 2017/1/12.
 */
@Service("AdminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminDAO adminDAO;

    public Admin getAdminByAccountAndPassword(String account, String password) {
        return adminDAO.getByAccountAndPassword(account, password);
    }
}
