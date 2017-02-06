package domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zky on 2017/2/7.
 */
@Entity
@Table(name = "application", catalog = "xf")
public class Application {

    private int id;
    private String person;
    private String type;
    private String detail;
    private String state;
    private String time;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "person")
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "detail")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", person='" + person + '\'' +
                ", type='" + type + '\'' +
                ", detail='" + detail + '\'' +
                ", state='" + state + '\'' +
                ", time=" + time +
                '}';
    }
}
