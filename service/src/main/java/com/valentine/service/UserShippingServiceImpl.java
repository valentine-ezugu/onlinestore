package com.valentine.service;

import com.valentine.domain.UserShipping;
import com.valentine.repository.UserShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@javax.transaction.Transactional
public class UserShippingServiceImpl implements UserShippingService {

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Override
    public UserShipping findById(long id) throws DataAccessException {
        return userShippingRepository.findOne(id);
    }

    @Override
    public void removeById(Long id) throws DataAccessException {
        userShippingRepository.delete(id);
    }
}
