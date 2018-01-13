package com.services.impl;

import com.data.persistence.UserShippingRepository;
import com.services.api.UserShippingService;
import com.domain.domain.UserShipping;
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
