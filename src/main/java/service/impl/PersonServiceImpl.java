package service.impl;

import DAO.PersonDAO;
import domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PersonService;

import java.util.List;

/**
 * Created by zky on 2017/1/15.
 */
@Service("PersonService")
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonDAO personDAO;

    public void addPerson(Person person) {
        personDAO.add(person);
    }

    public Person updatePassword(Person person) {
        Person oldPerson = personDAO.getByUsername(person.getUsername());
        oldPerson.setPassword(person.getPassword());
        personDAO.update(oldPerson);
        return oldPerson;
    }

    public Person updatePerson(Person person) {
        Person oldPerson = personDAO.getByUsername(person.getUsername());
        oldPerson.setClasses(person.getClasses());
        oldPerson.setNumber(person.getNumber());
        oldPerson.setPhone(person.getPhone());
        oldPerson.setQq(person.getQq());
        personDAO.update(oldPerson);
        return oldPerson;
    }

    public void deletePerson(Person person) {
        personDAO.delete(person);
    }

    public Person getPersonByUsername(String username) {
        return personDAO.getByUsername(username);
    }

    public Person getPersonByUsernameAndPassword(String username, String password) {
        return personDAO.getByUsernameAndPassword(username, password);
    }

    public Person getPersonById(int id) {
        return personDAO.getById(id);
    }

    public List<Person> getAllPerson() {
        return personDAO.getAll();
    }
}
