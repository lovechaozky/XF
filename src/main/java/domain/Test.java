package domain;

import javax.persistence.*;

/**
 * Created by zky on 2017/1/9.
 */
@Entity
@Table(name = "test", catalog = "xf")
public class Test {

    private String test1;
    private int id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "test1")
    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    @Override
    public String toString() {
        return "Test{" +
                "test1='" + test1 + '\'' +
                ", id=" + id +
                '}';
    }
}
