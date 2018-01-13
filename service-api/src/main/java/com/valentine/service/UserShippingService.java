package com.valentine.service;


import com.domain.domain.UserShipping;
import org.springframework.dao.DataAccessException;


public interface UserShippingService {
    UserShipping findById(long id) throws DataAccessException;

    void removeById(Long id) throws DataAccessException;
}
