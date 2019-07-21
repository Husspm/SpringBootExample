package com.hussey.springBootServer.dao;

import com.hussey.springBootServer.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        Optional<Person> first = DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
        return first;
    }
    @Override
    public Optional<Person> selectPersonByName(String name) {
        Optional<Person> first = DB.stream()
                .filter(person -> person.getName().equals(name))
                .findFirst();
        return first;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if (person.isPresent()) {
            DB.remove(person.get());
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int updatePersonById(UUID id, Person updatePerson) {
        return selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    if (indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(id, updatePerson.getName()));
                        return 1;
                    } else {
                        return 0;
                    }
                }).orElse(0);
    }
}
