package com.ticket.ticketstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.ticketstore.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
