package com.ticket.ticketstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ticket.ticketstore.dto.CustomerDTO;
import com.ticket.ticketstore.mapper.CustomerMapper;
import com.ticket.ticketstore.model.Customer;
import com.ticket.ticketstore.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDTO> getAllCustomers(){
        return customerMapper.customersToCustomerDTOs(customerRepository.findAll());
    }

    public CustomerDTO getCustomerById(Integer id){
        return customerMapper.customerToCustomerDTO(customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado")));
    }

    public CustomerDTO createCustomer(CustomerDTO dto){
        Customer customer = customerMapper.customerDTOToCustomer(dto);

        customer = customerRepository.save(customer);

        return customerMapper.customerToCustomerDTO(customer);
        
    }

    public CustomerDTO updateCustomer(CustomerDTO dto, Integer id){
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());

        customer = customerRepository.save(customer);

        return customerMapper.customerToCustomerDTO(customer);
    }

    public void deleteCustomer(Integer id){
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        customerRepository.deleteById(id);
    }
}
