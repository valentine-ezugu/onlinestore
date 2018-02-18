package com.valentine.service;


import com.valentine.domain.UserPayment;
import com.valentine.repository.UserPaymentRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserPaymentServiceImpl.class)
public class UserPaymentServiceTest {

    @Autowired
    private UserPaymentService userPaymentService;

    @MockBean
    private UserPaymentRepository userPaymentRepository;

    @Rule
    public final ExpectedException exception =  ExpectedException.none();

    @Test
    public  void findByIdTest() throws Exception {

        UserPayment userPayment = new UserPayment();
       when(userPaymentRepository.findOne(1L)).thenReturn(userPayment);

       UserPayment userPayment1 = userPaymentService.findById(1L);
        Assert.assertNotNull(userPayment1);

        Mockito.verify(userPaymentRepository).findOne(1L);
    }

    @Test
    public void removeByIdTest () throws Exception {
        UserPayment userPayment = new UserPayment();
         userPayment.setId(1L);

        userPaymentService.removeById(1L);

        Mockito.verify(userPaymentRepository).delete(1L);
    }

    @Test
    public void exceptionForFind() throws Exception {
        when(userPaymentRepository.findOne(1L)).thenThrow(new DataAccessException(""){
        });
        exception.expect(DataAccessException.class);
        userPaymentService.findById(1L);
    }

}
