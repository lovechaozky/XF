package domain;

import javax.persistence.*;

/**
 * Created by zky on 2017/1/12.
 */
@Entity
@Table(name = "admin", catalog = "xf")
public class Admin {

    private int id;
    private String account;
    private String password;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "account")
    public String getAccount() {
        return account;
    }


    public void setAccount(String account) {
        this.account = account;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
