package com.data.persistence;

import com.domain.domain.UserShipping;
import org.springframework.data.repository.CrudRepository;

public interface UserShippingRepository extends CrudRepository<UserShipping, Long> {

    UserShipping findOne(long id);
}
