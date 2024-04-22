package dev.simplesolution.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.simplesolution.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
