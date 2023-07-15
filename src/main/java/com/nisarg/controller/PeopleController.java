package com.nisarg.controller;

import com.nisarg.bean.Person;
import com.nisarg.repository.PersonRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class PeopleController {
  private PersonRepository personRepository;

  @PostMapping
  public ResponseEntity<Person> save(@RequestBody Person person) {
    return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> find(@PathVariable long id) {
    Person person = personRepository.findById(id).orElse(null);
    if (person == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(person, HttpStatus.FOUND);
    }
  }
  @GetMapping("/find/{name}")
  public ResponseEntity<Person> findByName(@PathVariable String name) {
    Person person = personRepository.findByName(name);
    if (person == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(person, HttpStatus.FOUND);
    }
  }

  @GetMapping
  public ResponseEntity<List<Person>> findAll() {
    return new ResponseEntity<>(personRepository.findAll(Sort.by(Order.by("name"))), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Person> delete(@PathVariable long id) {
    log.info("Person with id:{} deleted!", id);
    personRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<Person> deleteAll() {
    log.info("Deleted All People!");
    personRepository.deleteAll();
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
