package com.Ticket.TicketStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ticket.TicketStore.model.Customer;
import com.Ticket.TicketStore.model.CustomerTicket;
import com.Ticket.TicketStore.model.Ticket;
import com.Ticket.TicketStore.model.id.CustomerTicketId;
import java.util.List;


@Repository
public interface CustomerTicketRepository extends JpaRepository<CustomerTicket, CustomerTicketId>{
    List<CustomerTicket> findByCustomer(Customer customer);

    List<CustomerTicket> findByIdCustomerIdIn(List<Integer> customerIds);

    List<CustomerTicket> findByTicket(Ticket ticket);
}
