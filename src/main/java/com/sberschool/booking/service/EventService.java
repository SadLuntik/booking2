package com.sberschool.booking.service;

import com.sberschool.booking.dto.EventDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import java.sql.Date;
import java.util.List;

public interface EventService {
    Long acquire(EventDTO eventDTO);

    void updateEvent(EventDTO eventDTO);

    List<EventDTO> getListEventDTOFromPageableWithSpecificationStandard(String userName, Pageable pageable);

    List<EventDTO> getListEventDTOFromPageableWithSpecificationAdditional(String userName, String title, Date time, Pageable pageable);

    List<Integer> preparePageInt(int current, int totalPages);

    String getUserName(Long id);

    void deleteEvent(@PathVariable Long id);

    boolean checkNotValidDate(Date startTime, Date endTime);

    List<EventDTO> getAllEvents();
}