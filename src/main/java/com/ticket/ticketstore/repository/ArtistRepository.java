package com.ticket.ticketstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.ticketstore.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer>{

}
