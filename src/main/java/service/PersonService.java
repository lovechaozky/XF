package service;

import domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zky on 2017/1/15.
 */
@Service()
@Transactional
public interface PersonService {

    public void addPerson(Person person);

    public Person updatePerson(Person person);

    public Person updatePassword(Person person);

    public void deletePerson(Person person);

    public Person getPersonByUsername(String username);

    public Person getPersonByUsernameAndPassword(String username, String password);

    public Person getPersonById(int id);

    public List<Person> getAllPerson();

}
