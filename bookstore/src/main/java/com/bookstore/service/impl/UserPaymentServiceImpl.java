package com.bookstore.service.impl;

import com.bookstore.domain.UserPayment;
import com.bookstore.repository.UserPaymentRepository;
import com.bookstore.service.api.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;


@Service
@Transactional
public class UserPaymentServiceImpl implements UserPaymentService {
    @Autowired
    UserPaymentRepository userPaymentRepository;

    @Override
    public UserPayment findById(Long id)throws DataAccessException
    {
        return userPaymentRepository.findOne(id);
    }

    @Override
    public void removeById(Long id)throws DataAccessException{
       userPaymentRepository.delete(id);
    }
}
