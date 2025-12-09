package com.ticket.ticketstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.ticketstore.model.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer>{

}
