package com.vicaba.wisdompetapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.vicaba.wisdompetapi.data.entity.CustomerEntity;
import com.vicaba.wisdompetapi.data.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @InjectMocks
  CustomerService customerService;

  @Mock
  CustomerRepository customerRepository;

  @Test
  void getAllCustomers() {
    Mockito.doReturn(getMockCustomers(2)).when(customerRepository).findAll();
    final var customers = this.customerService.getAllCustomers();
    assertEquals(2, customers.size());
  }

  private Iterable<CustomerEntity> getMockCustomers(int size) {
    final var customers = new ArrayList<CustomerEntity>(size);
    for (int i = 0; i < size; i++) {
      final var customer = new CustomerEntity(UUID.randomUUID(), "First" + i, "Last" + i,
          "first" + i + "@last" + i + ".com", "123-456-7890", "Home");
      customers.add(customer);
    }
    return customers;
  }

  @Test
  void givenNoCustomersWhenFindingByEmailAddressTheResultIsNull() {
    Mockito.doReturn(null).when(customerRepository).findByEmailAddress(Mockito.anyString());
    final var customer = this.customerService.findByEmailAddress("");
    assertNull(customer);
  }

  @Test
  void givenACustomerWithMatchingEmailAddressWhenFindingByEmailAddressThenTheCustomerIsReturned() {
    final var customerToReturn = new CustomerEntity(UUID.randomUUID(), "First", "Last",
        "test@test.com", "123-456-7890", "Home");
    Mockito.doReturn(customerToReturn).when(customerRepository).findByEmailAddress("test@test.com");
    final var customer = this.customerService.findByEmailAddress("test@test.com");
    assertEquals(customerToReturn.getEmailAddress(), customer.getEmailAddress());
  }

}
