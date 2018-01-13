package com.bookstore.repository;

import com.domain.domain.*;
import org.springframework.data.repository.CrudRepository;

public interface UserShippingRepository extends CrudRepository<UserShipping, Long> {

    UserShipping findOne(long id);
}
