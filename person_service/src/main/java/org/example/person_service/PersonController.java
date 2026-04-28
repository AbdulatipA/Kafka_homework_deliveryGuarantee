package org.example.person_service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping()
    public ResponseEntity<PersonDTO> createPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.savePerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable long id) {
        personService.deletePersonById(id);
        return ResponseEntity.ok("Пользователь с id " + id + "удален");
    }
}
