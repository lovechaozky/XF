package service.impl;

import DAO.CounselingRoomDAO;
import DAO.querypage.Page;
import domain.CounselingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CounselingRoomService;

import java.util.List;

/**
 * Created by zky on 2017/2/10.
 */
@Service("CounselingRoom")
public class CounselingRoomServiceImpl implements CounselingRoomService{

    @Autowired
    CounselingRoomDAO counselingRoomDAO;

    public int getPageCount(int everyPage) {
        return counselingRoomDAO.getPageCount(everyPage);
    }

    public void addCounselingRoom(CounselingRoom counselingRoom) {
        counselingRoomDAO.add(counselingRoom);
    }

    public void updateCounselingRoom(CounselingRoom counselingRoom) {
        CounselingRoom oldCounselingRoom = counselingRoomDAO.getById(counselingRoom.getId());
        if(counselingRoom.getTime() != null && !"".equals(counselingRoom.getTime()))
            oldCounselingRoom.setTime(counselingRoom.getTime());
        if(counselingRoom.getPlace() != null && !"".equals(counselingRoom.getPlace()))
            oldCounselingRoom.setPlace(counselingRoom.getPlace());
        counselingRoomDAO.update(oldCounselingRoom);
    }

    public List<CounselingRoom> getCounselingRoomByTime(String time, Page page) {
        return counselingRoomDAO.getByTime(time, page);
    }

    public List<CounselingRoom> getCounselingRoomByPlace(String place, Page page) {
        return counselingRoomDAO.getByPlace(place, page);
    }
}
