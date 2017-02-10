package DAO.impl;

import DAO.CounselingRoomDAO;
import DAO.querypage.Page;
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

    public int getPageCount(int everyPage) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("select count(*) from CounselingRoom");
        int rowCount = (Integer)query.uniqueResult();
        session.close();
        if(rowCount == 0)
            return 0;
        else{
            if(rowCount % everyPage == 0)
                return rowCount / everyPage;
            else
                return rowCount / everyPage + 1;
        }
    }

    public void add(CounselingRoom counselingRoom) {
        super.save(counselingRoom);
    }

    public void update(CounselingRoom counselingRoom){
        super.update(counselingRoom);
    }

    public CounselingRoom getById(int id) {
        return super.get(CounselingRoom.class, id);
    }

    public List<CounselingRoom> getByTime(String time, Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from CounselingRoom where time=?");
        query.setString(0, time);
        query.setFirstResult(page.getBeginIndex());
        query.setMaxResults(page.getEveryPage());
        List<CounselingRoom> counselingRooms = query.list();
        session.close();
        return counselingRooms;
    }

    public List<CounselingRoom> getByPlace(String place, Page page) {
        Session session = getSessionFactory().openSession();
        Query query = session.createQuery("from CounselingRoom where place=?");
        query.setString(0, place);
        query.setFirstResult(page.getBeginIndex());
        query.setMaxResults(page.getEveryPage());
        List<CounselingRoom> counselingRooms = query.list();
        session.close();
        return counselingRooms;
    }
}
