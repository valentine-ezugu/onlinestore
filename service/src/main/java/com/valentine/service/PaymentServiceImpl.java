package com.valentine.service;

import com.valentine.domain.Payment;
import com.valentine.domain.UserPayment;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    public Payment setByUserPayment(UserPayment userPayment, Payment payment) throws AccessDeniedException

    {
        payment.setType(userPayment.getType());
        payment.setHolderName(userPayment.getHolderName());
        payment.setCardNumber(userPayment.getCardNumber());
        payment.setExpiryMonth(userPayment.getExpiryMonth());
        payment.setExpiryYear(userPayment.getExpiryYear());
        payment.setCvc(userPayment.getCvc());

        return payment;
    }

}
