package com.ticket.ticketstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.ticketstore.model.Customer;
import com.ticket.ticketstore.model.CustomerTicket;
import com.ticket.ticketstore.model.Ticket;
import com.ticket.ticketstore.model.id.CustomerTicketId;

import java.util.List;


@Repository
public interface CustomerTicketRepository extends JpaRepository<CustomerTicket, CustomerTicketId>{
    List<CustomerTicket> findByCustomer(Customer customer);

    List<CustomerTicket> findByIdCustomerIdIn(List<Integer> customerIds);

    List<CustomerTicket> findByTicket(Ticket ticket);
}
