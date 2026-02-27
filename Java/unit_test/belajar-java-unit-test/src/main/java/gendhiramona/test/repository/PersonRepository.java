package gendhiramona.test.repository;

import gendhiramona.test.data.Person;

public interface PersonRepository {

    Person selectById(String id);

    void insert(Person person);
}
