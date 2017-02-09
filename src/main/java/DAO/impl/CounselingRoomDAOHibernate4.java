package DAO.impl;

import DAO.CounselingRoomDAO;
import domain.CounselingRoom;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/2/10.
 */
@Component("CounselingRoomDAOHibernate4")
public class CounselingRoomDAOHibernate4 extends BaseDAOHibernate4<CounselingRoom> implements CounselingRoomDAO{

    public void add(CounselingRoom counselingRoom) {
        super.save(counselingRoom);
    }

    public void update(CounselingRoom counselingRoom){
        super.update(counselingRoom);
    }

    public CounselingRoom getById(int id) {
        return super.get(CounselingRoom.class, id);
    }

    public List<CounselingRoom> getByTime(String time) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from CounselingRoom where time=?");
        query.setString(0, time);
        List<CounselingRoom> counselingRooms = query.list();
        session.close();
        return counselingRooms;
    }

    public List<CounselingRoom> getByPlace(String place) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from CounselingRoom where place=?");
        query.setString(0, place);
        List<CounselingRoom> counselingRooms = query.list();
        session.close();
        return counselingRooms;
    }
}
