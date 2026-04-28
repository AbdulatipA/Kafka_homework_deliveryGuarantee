package org.example.order_service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    EventPerson toEventPerson(Order order);
    OrderDTO toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "id", target = "clientId")
    @Mapping(source = "name", target = "clientName")
    Order toEntity(EventPerson eventPerson);
}
