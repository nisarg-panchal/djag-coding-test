package com.nisarg.controller;

import com.nisarg.bean.Person;
import com.nisarg.repository.PersonRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

  @Operation(
      description = "Save a new person into database table",
      responses = {
          @ApiResponse(description = "Returns 201 AKA Created if an insert is successful", responseCode = "201")
      }
  )
  @PostMapping
  public ResponseEntity<Person> save(@RequestBody Person person) {
    return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
  }

  @Operation(
      description = "Find a person by primary key ID value",
      responses = {
          @ApiResponse(description = "If successful then returns all fields as JSON for a person with ID supplied as a parameter", responseCode = "302"),
          @ApiResponse(description = "If a person is not found, then only 404 AKA not found is returned", responseCode = "404")
      }
  )
  @GetMapping("/{id}")
  public ResponseEntity<Person> find(@PathVariable long id) {
    Person person = personRepository.findById(id).orElse(null);
    if (person == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(person, HttpStatus.FOUND);
    }
  }

  @Operation(
      description = "Find a person by primary key ID value",
      responses = {
          @ApiResponse(description = "If successful then returns all fields as JSON for a person with ID supplied as a parameter search matching whole name", responseCode = "302"),
          @ApiResponse(description = "If a person is not found, then only 404 AKA not found is returned", responseCode = "404")
      }
  )
  @GetMapping("/find/{name}")
  public ResponseEntity<Person> findByName(@PathVariable String name) {
    Person person = personRepository.findByName(name);
    if (person == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return new ResponseEntity<>(person, HttpStatus.FOUND);
    }
  }

  @Operation(
      description = "Find All People from database",
      responses = {
          @ApiResponse(description = "Returns all people from database table", responseCode = "302")
      }
  )
  @GetMapping
  public ResponseEntity<List<Person>> findAll() {
    return new ResponseEntity<>(personRepository.findAll(Sort.by(Order.by("name"))), HttpStatus.OK);
  }

  @Operation(
      description = "Delete a person with a given ID/primary-key",
      responses = {
          @ApiResponse(description = "Returns 200 along with the deleted person as JSON output", responseCode = "200")
      }
  )
  @DeleteMapping("/{id}")
  public ResponseEntity<Person> delete(@PathVariable long id) {
    log.info("Person with id:{} deleted!", id);
    personRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Operation(
      description = "Delete all people from database",
      responses = {
          @ApiResponse(description = "Returns http status OK", responseCode = "200")
      }
  )
  @DeleteMapping
  public ResponseEntity deleteAll() {
    log.info("Deleted All People!");
    personRepository.deleteAll();
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
