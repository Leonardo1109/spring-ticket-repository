package com.ticket.ticketstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.ticketstore.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
