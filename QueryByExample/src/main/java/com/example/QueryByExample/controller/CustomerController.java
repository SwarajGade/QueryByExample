package com.example.QueryByExample.controller;

import com.example.QueryByExample.model.Customer;
import com.example.QueryByExample.repo.CustomerRepository;
import com.example.QueryByExample.service.CustomerQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerQueryService queryService;

    @Autowired
    private CustomerRepository repository;

    private final Map<String, String> countryMap = Map.of(
            "BR", "Brazil",
            "CH", "China"
    );
    
    @GetMapping("search")
    public Flux<Customer> search(Customer customer){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withTransformer("country", op -> op.map(c -> countryMap.getOrDefault(c, "UNKNOWN")));
        return this.repository.findAll(Example.of(customer, matcher));
    }

}