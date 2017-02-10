package domain;

import javax.persistence.*;

/**
 * Created by zky on 2017/2/11.
 */
@Entity
@Table(name = "communication", catalog = "xf")
public class Communication {

    private int id;
    private String person;
    private String time;
    private String message;

    @Override
    public String toString() {
        return "Communication{" +
                "id=" + id +
                ", person='" + person + '\'' +
                ", time='" + time + '\'' +
                ", message='" + message + '\'' +
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

    @Column(name = "person")
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
