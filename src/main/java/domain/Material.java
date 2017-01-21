package domain;

import javax.persistence.*;

/**
 * Created by zky on 2017/1/22.
 */
@Entity
@Table(name = "material", catalog = "xf")
public class Material {

    private int id;
    private String title;
    private String address;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
