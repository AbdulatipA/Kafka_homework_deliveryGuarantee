package org.example.person_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepo personRepository;
    private final PersonMapper personMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.producer.topics}")
    private String personTopic;


    public PersonDTO savePerson(Person person) {
        Person personSaved = personRepository.save(person);
        PersonDTO personDTO = personMapper.toDto(personSaved);

        //маппер в eventPerson из сущности, отправка в kafka
        EventPerson eventPerson = personMapper.toEventPerson(personSaved);
        kafkaTemplate.send(personTopic, eventPerson);
        log.info("Сообщение отправлено в orderService");

        return personDTO;
    }

    public PersonDTO getPersonById(long id) {
        Person person = personRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Пользователь с id:" + id + "не найден"));

        PersonDTO personDTO = personMapper.toDto(person);
        return personDTO;
    }

    public List<PersonDTO> getAllPersons() {
        List<Person> personsList = personRepository.findAll();

        List<PersonDTO> personsDTOList = personsList.stream()
                .map(person -> personMapper.toDto(person))
                .collect(Collectors.toList());

        return personsDTOList;
    }

    public void deletePersonById(long id) {
        personRepository.deleteById(id);
    }
}
