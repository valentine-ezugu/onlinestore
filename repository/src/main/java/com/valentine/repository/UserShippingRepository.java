package com.valentine.repository;

import com.valentine.domain.UserShipping;
import org.springframework.data.repository.CrudRepository;

public interface UserShippingRepository extends CrudRepository<UserShipping, Long> {

    UserShipping findOne(long id);
}
