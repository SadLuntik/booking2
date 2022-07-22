package com.sberschool.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFilterDTO {
    private List<EventDTO> eventDTOs;
    private Date time;
    private String title;
}
