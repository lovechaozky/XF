package service;

import DAO.querypage.Page;
import domain.CounselingRoom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zky on 2017/2/10.
 */
@Service()
@Transactional
public interface CounselingRoomService {

    public int getPageCount(int everyPage);

    public void addCounselingRoom(CounselingRoom counselingRoom);

    public void updateCounselingRoom(CounselingRoom counselingRoom);

    public List<CounselingRoom> getCounselingRoomByTime(String time, Page page);

    public List<CounselingRoom> getCounselingRoomByPlace(String place, Page page);

}
