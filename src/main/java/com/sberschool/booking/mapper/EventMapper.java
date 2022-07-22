package com.sberschool.booking.mapper;

import com.sberschool.booking.dto.EventDTO;
import com.sberschool.booking.entity.Event;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO toEvenDTO(Event event);

    Event toEvent(EventDTO eventDTO);

    List<EventDTO> toEvenDTO(List<Event> eventList);

    List<Event> toEvent(List<EventDTO> eventDTOList);
}
