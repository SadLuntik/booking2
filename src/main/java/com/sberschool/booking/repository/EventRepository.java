package com.sberschool.booking.repository;

import com.sberschool.booking.entity.Event;
import com.sberschool.booking.specification.EventSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    List<Event> getByResourceTitle(String title);

    @Query("SELECT u FROM Event u WHERE  u.resourceTitle = ?1 and (u.startTime between ?2 and ?3 or u.endTime between ?2 and ?3 )")
    List<Event> findByDateInstanceDuration(String title, Date start, Date end);

    default Page<Event> filter(String userName, String title, Date time, Pageable pageable){
        Specification<Event> specification = Specification.where(null);
        specification = specification.and(EventSpecification.userNameEqual(userName));
        if (title != null) {
            specification = specification.and(EventSpecification.findByTitle(title));
        }
        if (time != null) {
            specification = specification.and(EventSpecification.timeEqual(time));
        }
        return findAll(specification,pageable);
    }

    default Page<Event> filter(String userName,  Pageable pageable){
        Specification<Event> specification = Specification.where(null);
        specification = specification.and(EventSpecification.userNameEqual(userName));
        return findAll(specification,pageable);
    }
}