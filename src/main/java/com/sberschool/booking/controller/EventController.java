package com.sberschool.booking.controller;

import com.sberschool.booking.dto.EventDTO;
import com.sberschool.booking.service.UserService;
import com.sberschool.booking.service.impl.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventServiceImpl eventService;
    private final UserService userService;

    private static final String EVENT = "event";
    private static final String EVENTS = "events";
    private static final String ID = "id";
    private static final String REDIRECT_EVENT = "redirect:/event";

    @GetMapping
    public String showProductsList(Model model,
                                   EventDTO eventDTO,
                                   Pageable pageable) {

        List<EventDTO> eventDTOList = eventService.getListEventDTOFromPageableWithSpecificationStandard(userService.getUserName(), pageable);

        model.addAttribute(EVENTS, eventDTOList);
        model.addAttribute(EVENT, eventDTO);
        model.addAttribute("userName", userService.getUserName());

        return "event";
    }

    @PostMapping("/add")
    public String addEvent(Model model, EventDTO eventDTO) {
        model.addAttribute(EVENT, eventDTO);
        eventService.acquire(eventDTO);
        return REDIRECT_EVENT;
    }

    @GetMapping("/filter")
    public String filterEvent(Model model,
                              @RequestParam(value = "time", required = false) Date time,
                              @RequestParam(value = "title", required = false) String title,
                              EventDTO eventDTO,
                              Pageable pageable) {
        List<EventDTO> eventDTOList = eventService.getListEventDTOFromPageableWithSpecificationAdditional(userService.getUserName(), title, time, pageable);
        model.addAttribute("userName", userService.getUserName());
        model.addAttribute(EVENTS, eventDTOList);
        model.addAttribute(EVENT, eventDTO);
        return EVENT;
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = ID) Long id) {
        eventService.deleteEvent(id);
        return REDIRECT_EVENT;
    }
}