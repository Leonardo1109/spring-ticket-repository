package com.Ticket.TicketStore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.Ticket.TicketStore.dto.CustomerDTO;
import com.Ticket.TicketStore.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);

    List<CustomerDTO> customersToCustomerDTOs(List<Customer> customers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerTickets", ignore = true)
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerTickets", ignore = true)
    List<Customer> customerDTOsToCustomers(List<CustomerDTO> customerDTOs);
}
