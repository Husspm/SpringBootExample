package com.hussey.springBootServer.api;

import com.hussey.springBootServer.model.Person;
import com.hussey.springBootServer.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @CrossOrigin
    @GetMapping(path = "create/new")
    public String Test() {
        return "This route is working correctly";
    }
    @CrossOrigin
    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person)
    {
        personService.addPerson(person);
    }
    @CrossOrigin
    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }
    @GetMapping(path = "full/{id}")
    public Person getPersonById (@PathVariable("id") UUID id) {
        return personService.getPersonById(id)
                .orElse(null);
    }
    @GetMapping(path = "{name}")
    public Person getPersonByName (@PathVariable("name") String name) {
        return personService.getPersonByName(name)
                .orElse(null);
    }
    @GetMapping(path = "newPage/new")
    public String getIndexPage() {
        return "index";
    }
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
}
