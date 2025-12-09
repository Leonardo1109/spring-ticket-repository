package com.Ticket.TicketStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ticket.TicketStore.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
