package DAO;

import DAO.querypage.Page;
import domain.Communication;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/2/11.
 */
@Component
public interface CommunicationDAO extends BaseDAO<Communication>{

    public int getPageCount(int everyPage);

    public void add(Communication communication);

    public void delete(Communication communication);

    public void update(Communication communication);

    public void delete(int id);

    public Communication getById(int id);

    public List<Communication> getByTime(String time, Page page);

    public List<Communication> getByPerson(String person, Page page);

    public List<Communication> getAll(Page page);

}
