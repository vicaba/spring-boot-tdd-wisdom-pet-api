package com.vicaba.wisdompetapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.vicaba.wisdompetapi.web.error.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CustomerServiceIntegrationTest {

  @Autowired
  CustomerService customerService;

  @Test
  void getAllCustomers() {
    final var customers = this.customerService.getAllCustomers();
    assertEquals(5, customers.size());
  }

  @Test
  void findByEmailAddress_Exists() {
    final var customer = this.customerService.findByEmailAddress("penatibus.et@lectusa.com");
    assertNotNull(customer);
  }

  @Test
  void findByEmailAddress_NoExists() {
    final var customer = this.customerService.findByEmailAddress("noexists@test.com");
    assertNull(customer);
  }

  @Test
  void getCustomer_Exists() {
    final var customer = this.customerService.getCustomer("054b145c-ddbc-4136-a2bd-7bf45ed1bef7");
    assertNotNull(customer);
  }

  @Test
  void getCustomer_NoExists() {
    assertThrows(NotFoundException.class, () -> this.customerService.getCustomer("354b145c-ddbc-4136-a2bd-7bf45ed1bef7"));
  }

}
