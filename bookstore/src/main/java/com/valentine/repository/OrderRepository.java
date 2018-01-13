package com.valentine.repository;

import com.domain.domain.*;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<Order, Long> {

}
