package DAO;

import domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zky on 2017/1/15.
 */
@Component
public interface PersonDAO extends BaseDAO<Person>{

    public void add(Person person);

    public void update(Person person);

    public void delete(Person person);

    public Person getByUsername(String username);

    public Person getByUsernameAndPassword(String username, String password);

    public Person getById(int id);

    public List<Person> getAll();

}
