package domain;

import javax.persistence.*;

/**
 * Created by zky on 2017/2/10.
 */
@Entity
@Table(name = "counselingroom", catalog = "xf")
public class CounselingRoom {

    private int id;
    private String place;
    private String time;

    @Override
    public String toString() {
        return "CounselingRoom{" +
                "id=" + id +
                ", place='" + place + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "place")
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
