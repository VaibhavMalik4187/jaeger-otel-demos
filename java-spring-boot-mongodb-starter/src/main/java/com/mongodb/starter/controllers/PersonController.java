package com.mongodb.starter.controllers;

import com.mongodb.starter.dtos.PersonDTO;
import com.mongodb.starter.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @WithSpan
    @PostMapping("person")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO postPerson(@RequestBody @SpanAttribute("personDTO") PersonDTO PersonDTO) {
        return personService.save(PersonDTO);
    }

    @WithSpan
    @PostMapping("persons")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PersonDTO> postPersons(@RequestBody @SpanAttribute("personEntities") List<PersonDTO> personEntities) {
        return personService.saveAll(personEntities);
    }

    @WithSpan
    @GetMapping("persons")
    public List<PersonDTO> getPersons() {
        Span span = Span.current();
        span.setAttribute("Test attribute", "Working fine");
        return personService.findAll();
    }

    @WithSpan
    @GetMapping("person/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable @SpanAttribute("id") String id) {
        PersonDTO PersonDTO = personService.findOne(id);
        if (PersonDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(PersonDTO);
    }

    @WithSpan
    @GetMapping("persons/{ids}")
    public List<PersonDTO> getPersons(@PathVariable @SpanAttribute("ids") String ids) {
        List<String> listIds = List.of(ids.split(","));
        return personService.findAll(listIds);
    }

    @WithSpan
    @GetMapping("persons/count")
    public Long getCount() {
        return personService.count();
    }

    @WithSpan
    @DeleteMapping("person/{id}")
    public Long deletePerson(@PathVariable @SpanAttribute("id") String id) {
        return personService.delete(id);
    }

    @WithSpan
    @DeleteMapping("persons/{ids}")
    public Long deletePersons(@PathVariable @SpanAttribute("ids") String ids) {
        List<String> listIds = List.of(ids.split(","));
        return personService.delete(listIds);
    }

    @WithSpan
    @DeleteMapping("persons")
    public Long deletePersons() {
        return personService.deleteAll();
    }

    @WithSpan
    @PutMapping("person")
    public PersonDTO putPerson(@RequestBody @SpanAttribute("personDTO") PersonDTO PersonDTO) {
        return personService.update(PersonDTO);
    }

    @WithSpan
    @PutMapping("persons")
    public Long putPerson(@RequestBody @SpanAttribute("personEntities") List<PersonDTO> personEntities) {
        return personService.update(personEntities);
    }

    @WithSpan
    @GetMapping("persons/averageAge")
    public Double averageAge() {
        return personService.getAverageAge();
    }

    @WithSpan
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(@SpanAttribute("runtimeException") RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}
