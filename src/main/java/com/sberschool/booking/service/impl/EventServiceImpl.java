package com.sberschool.booking.service.impl;

import com.sberschool.booking.dto.EventDTO;
import com.sberschool.booking.entity.*;
import com.sberschool.booking.exception.EventBookedException;
import com.sberschool.booking.exception.NotValidDateException;
import com.sberschool.booking.mapper.EventMapper;
import com.sberschool.booking.repository.EventRepository;
import com.sberschool.booking.service.EventService;
import com.sberschool.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    private final UserService userService;

    @Override
    public Long acquire(EventDTO eventDTO) {
        if (checkNotValidDate(eventDTO.getStartTime(), eventDTO.getEndTime())) {
            throw new NotValidDateException();
        }
        Event EVENT = eventMapper.toEvent(eventDTO);
        if (eventRepository.findByDateInstanceDuration(
                EVENT.getResourceTitle(),
                EVENT.getStartTime(),
                EVENT.getEndTime()
        ).size() > 0) {
            throw new EventBookedException();
        }
        updateEvent(eventDTO);

        return eventDTO.getId();
    }

    @Override
    public void updateEvent(EventDTO eventDTO) {
        eventDTO.setUserName(userService.getUserName());
        Event event = eventMapper.toEvent(eventDTO);
        eventRepository.save(event);
    }
    @Override
    public List<EventDTO> getListEventDTOFromPageableWithSpecificationStandard(String userName, Pageable pageable) {
        return eventMapper.toEvenDTO(eventRepository.filter(userName, pageable).getContent());
    }

    @Override
    public List<EventDTO> getListEventDTOFromPageableWithSpecificationAdditional(String userName, String title, Date time, Pageable pageable) {
        return eventMapper.toEvenDTO(eventRepository.filter(userName, title, time, pageable).getContent());
    }
    @Override
    public List<Integer> preparePageInt(int current, int totalPages) {

        List<Integer> pageNumbers = new ArrayList<>();
        int start = Math.max(current - 5, 0);
        int end = Math.min(totalPages, start + 11);
        for (int i = start; i < end; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers;
    }

    @Override
    public String getUserName(Long id) {
        return eventRepository.getReferenceById(id).getUserName();
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.delete(eventRepository.findById(id).orElseThrow());
    }

    @Override
    public boolean checkNotValidDate(Date startTime, Date endTime) {
        long currentTime = System.currentTimeMillis();
        long currentTimePlus20Years = currentTime + 20 * 31536000000L;
        return startTime.after(new java.util.Date(currentTimePlus20Years)) ||
                startTime.before(new java.util.Date(currentTime)) ||
                endTime.after(new java.util.Date(currentTimePlus20Years)) ||
                endTime.before(new java.util.Date(currentTime)) ||
                startTime.after(endTime);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventMapper.toEvenDTO(eventRepository.findAll());
    }

}