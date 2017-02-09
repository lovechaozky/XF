package DAO;

import domain.CounselingRoom;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/2/10.
 */
@Component
public interface CounselingRoomDAO extends BaseDAO<CounselingRoom>{

    public void add(CounselingRoom counselingRoom);

    public void update(CounselingRoom counselingRoom);

    public CounselingRoom getById(int id);

    public List<CounselingRoom> getByTime(String time);

    public List<CounselingRoom> getByPlace(String place);

}
