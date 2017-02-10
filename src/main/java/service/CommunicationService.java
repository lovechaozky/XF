package service;

import DAO.querypage.Page;
import domain.Communication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zky on 2017/2/11.
 */
@Service()
@Transactional
public interface CommunicationService {

    public int getPageCount(int everyPage);

    public void addCommunication(Communication communication);

    public void updateCommunication(Communication communication);

    public void deleteByItem(Communication communication);

    public void deleteById(int id);

    public List<Communication> getCommunicationByTime(String time, Page page);

    public List<Communication> getCommunicationByPerson(String person, Page page);

    public List<Communication> getAllCommunication(Page page);

}
