package com.valentine.bookstore.service;

import com.bookstore.domain.UserShipping;
import com.bookstore.repository.UserShippingRepository;
import com.bookstore.services.api.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Created by Pc on 8/31/2017.
 */
@Service
@javax.transaction.Transactional
public class UserShippingServiceImpl implements UserShippingService {

    @Autowired
    UserShippingRepository userShippingRepository;

    @Override
    public UserShipping findById(long id) throws DataAccessException {
        return userShippingRepository.findOne(id);
    }

    @Override
    public void removeById(Long id) throws DataAccessException {
        userShippingRepository.delete(id);
    }
}
