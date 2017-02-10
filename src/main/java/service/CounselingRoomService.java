package service;

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

    public void addCounselingRoom(CounselingRoom counselingRoom);

    public void updateCounselingRoom(CounselingRoom counselingRoom);

    public List<CounselingRoom> getCounselingRoomByTime(String time);

    public List<CounselingRoom> getCounselingRoomByPlace(String place);

}
