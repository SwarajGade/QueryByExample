package com.example.QueryByExample.repo;

import com.example.QueryByExample.model.Customer;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>, ReactiveQueryByExampleExecutor<Customer> {
}
