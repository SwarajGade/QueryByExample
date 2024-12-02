package com.example.QueryByExample.service;

import com.example.QueryByExample.model.Customer;
import com.example.QueryByExample.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CustomerQueryService {

    @Autowired
    private CustomerRepository repository;

    public Flux<Customer> search(Customer customer){
        return this.repository.findAll(Example.of(customer));
    }

}