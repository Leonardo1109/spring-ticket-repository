package com.Ticket.TicketStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ticket.TicketStore.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer>{

}
