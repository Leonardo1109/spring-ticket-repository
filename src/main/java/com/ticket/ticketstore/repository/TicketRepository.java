package com.ticket.ticketstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticket.ticketstore.model.*;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

    List<Ticket> findByEvent(Event event);

    List<Ticket> findByPriceGreaterThanOrderByPriceDesc(float price);

    @Query("""
            SELECT t
            FROM Ticket t
            JOIN t.event e
            JOIN e.venue v
            WHERE v.name = :venueName
    """)
    List<Ticket> findTicketsByVenueName(@Param("venueName") String venueName);


}
