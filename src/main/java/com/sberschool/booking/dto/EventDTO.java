package com.sberschool.booking.dto;

import lombok.*;
import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long id;
    private String resourceTitle;
    private String userName;
    private Date endTime;
    private Date startTime;
}