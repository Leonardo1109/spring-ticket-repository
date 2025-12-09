package com.Ticket.TicketStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ticket.TicketStore.model.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer>{

}
