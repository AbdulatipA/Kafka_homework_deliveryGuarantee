package org.example.person_service;

@org.mapstruct.Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO toDto(Person person);
    EventPerson toEventPerson(Person person);
}
