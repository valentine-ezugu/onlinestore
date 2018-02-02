package com.valentine.service;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.valentine.domain.Payment;
import com.valentine.domain.UserPayment;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PaymentServiceImpl.class)
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    public void setByUserPaymentTest() throws Exception{
        UserPayment userPayment = new UserPayment();
        userPayment.setCardName("val");
        userPayment.setCardNumber("2323 3232 24424");

        Payment payment = new Payment();


        payment.setType(userPayment.getType());
        payment.setHolderName(userPayment.getHolderName());
        payment.setCardNumber(userPayment.getCardNumber());
        payment.setExpiryMonth(userPayment.getExpiryMonth());
        payment.setExpiryYear(userPayment.getExpiryYear());
        payment.setCvc(userPayment.getCvc());

        Payment newPayment = paymentService.setByUserPayment(userPayment,payment);

        Assert.assertNotNull(newPayment);
        Assert.assertEquals(newPayment.getType(), userPayment.getType());
        Assert.assertEquals("val", userPayment.getCardName());

    }
}
