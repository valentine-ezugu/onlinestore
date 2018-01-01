package com.bookstore.repository;

import com.bookstore.domain.*;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<Order,Long> {

}
