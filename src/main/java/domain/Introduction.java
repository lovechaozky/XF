package domain;

import javax.persistence.*;

/**
 * Created by zky on 2017/1/12.
 */
@Entity
@Table(name = "introduction", catalog = "xf")
public class Introduction {

    private int id;
    private String introduction;
    private String constitution;
    private String rule;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column(name = "constitution")
    public String getConstitution() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }

    @Column(name = "rule")
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "Introduction{" +
                "id=" + id +
                ", introduction='" + introduction + '\'' +
                ", constitution='" + constitution + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }
}
