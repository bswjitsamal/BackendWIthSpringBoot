package dev.simplesolution.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.simplesolution.customer.model.Customer;
import dev.simplesolution.customer.repo.CustomerRepository;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class CustomerController {
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository custRepo;

	@GetMapping("/customers")
	public ResponseEntity<Object> getAllCustomers() {
		try {
			Iterable<Customer> custs = custRepo.findAll();
			return new ResponseEntity<Object>(custs, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable("id") Long id) {
		try {
			Customer cust = custRepo.findById(id).get();
			if (cust != null) {
				return new ResponseEntity<Object>(cust, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(cust, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/customers")
	public ResponseEntity<Object> createCustomer(@RequestBody Customer cust) {
		try {
			Customer saveCust = custRepo.save(cust);
			return new ResponseEntity<Object>(saveCust, HttpStatus.CREATED);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer cust, @PathVariable("id") Long id) {
		try {
			cust.setId(id);
			Customer updataCust = custRepo.save(cust);
			return new ResponseEntity<Object>(updataCust, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Long id) {
		try {
			custRepo.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}

}
