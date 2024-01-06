package com.vicaba.wisdompetapi.data.repository;

import com.vicaba.wisdompetapi.data.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface CustomerRepository extends CrudRepository<CustomerEntity, UUID> {
  CustomerEntity findByEmailAddress(String emailAddress);
}
