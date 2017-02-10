package service.impl;

import DAO.CommunicationDAO;
import DAO.querypage.Page;
import domain.Communication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CommunicationService;

import java.util.List;

/**
 * Created by zky on 2017/2/11.
 */
@Service("CommunicationService")
public class CommunicationServiceImpl implements CommunicationService{

    @Autowired
    CommunicationDAO communicationDAO;

    public int getPageCount(int everyPage) {
        return communicationDAO.getPageCount(everyPage);
    }

    public void addCommunication(Communication communication) {
        communicationDAO.add(communication);
    }

    public void updateCommunication(Communication communication) {
        Communication oldCommunication = communicationDAO.getById(communication.getId());
        if(communication.getMessage() != null && !"".equals(communication.getMessage()))
            oldCommunication.setMessage(communication.getMessage());
        communicationDAO.update(oldCommunication);
    }

    public void deleteByItem(Communication communication) {
        communicationDAO.delete(communication);
    }

    public void deleteById(int id) {
        communicationDAO.delete(id);
    }

    public List<Communication> getCommunicationByTime(String time, Page page) {
        return communicationDAO.getByTime(time, page);
    }

    public List<Communication> getCommunicationByPerson(String person, Page page) {
        return communicationDAO.getByPerson(person, page);
    }

    public List<Communication> getAllCommunication(Page page) {
        return communicationDAO.getAll(page);
    }
}
