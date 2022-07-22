package com.sberschool.booking.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "event")
@Setter
@Getter
@NoArgsConstructor
public class Event {

    public Event(Date startTime, Date endTime,String resourceTitle) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.resourceTitle = resourceTitle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resource_title")
    private String resourceTitle;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

}