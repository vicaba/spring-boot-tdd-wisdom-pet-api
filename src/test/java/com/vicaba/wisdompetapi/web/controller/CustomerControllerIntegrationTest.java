package com.vicaba.wisdompetapi.web.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vicaba.wisdompetapi.data.entity.CustomerEntity;
import com.vicaba.wisdompetapi.web.model.Customer;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CustomerControllerIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void getAllCustomers() throws Exception {
    this.mockMvc.perform(get("/customers"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("054b145c-ddbc-4136-a2bd-7bf45ed1bef7")))
        .andExpect(content().string(containsString("38124691-9643-4f10-90a0-d980bca0b27d")));
  }

  @Test
  void addCustomer() throws Exception {
    final var customer = new Customer("", "First", "Last",
        "test@test.com", "123-456-7890", "Home");
    final var json = this.objectMapper.writeValueAsString(customer);
    this.mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isCreated());
  }
}
