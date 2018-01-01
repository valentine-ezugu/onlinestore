package com.bookstore.services.api;


import com.bookstore.domain.*;
import org.springframework.dao.DataAccessException;

/**
 * Created by Pc on 8/31/2017.
 */
public interface UserShippingService {
     UserShipping findById(long id) throws DataAccessException;

     void removeById(Long id) throws DataAccessException;
 }
