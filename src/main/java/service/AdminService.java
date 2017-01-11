package service;

import domain.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zky on 2017/1/12.
 */
@Service()
@Transactional
public interface AdminService {

    public Admin getAdminByAccountAndPassword(String account, String password);

}
