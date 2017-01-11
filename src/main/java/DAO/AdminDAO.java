package DAO;

import domain.Admin;
import org.springframework.stereotype.Component;

/**
 * Created by zky on 2017/1/12.
 */
@Component
public interface AdminDAO extends BaseDAO<Admin>{

    public Admin getByAccountAndPassword(String account, String password);

}
