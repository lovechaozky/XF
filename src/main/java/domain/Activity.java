package domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by XiaoXiaoBing on 2017/1/14.
 */
@Entity
@Table(name = "activity",catalog = "xf")
public class Activity {
    private int id;
    private String title;
    private Date date;
    private String address;
    public Activity(){
        super();
    }
    public Activity(String title,Date date,String address){
        super();
        setTitle(title);
        setDate(date);
        setAddress(address);
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    @Column(name = "date")
    public Date getDate(){
        return this.date;
    }
    public void setDate(Date date){
        this.date = date;
    }

    @Column(name = "address")
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                '}';
    }
}
