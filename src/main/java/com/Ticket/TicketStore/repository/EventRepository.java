package com.Ticket.TicketStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Ticket.TicketStore.model.Event;
import java.time.LocalDate;


public interface EventRepository extends JpaRepository<Event, Integer>{
    // Buscar por titulo (%%) Y Lower
    List<Event> findByTitleContainingIgnoreCase(String title);

    List<Event> findByDate(LocalDate date);

    @Query("""
        SELECT e
        FROM Event e
        JOIN e.artists a
        WHERE a.name = :artistsName
    """)
    List<Event> findEventsByArtistsName(@Param("artistsName") String artistsName);

}
