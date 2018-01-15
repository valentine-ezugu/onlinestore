package com.valentine.bookstore.repository;

import com.valentine.domain.Order;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<Order, Long> {

}
