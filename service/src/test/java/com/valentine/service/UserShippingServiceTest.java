package com.valentine.service;


import com.valentine.domain.UserShipping;
import com.valentine.repository.UserShippingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserShippingServiceImpl.class)
public class UserShippingServiceTest {

    @Autowired
    private UserShippingService userShippingService;

    @MockBean
    private UserShippingRepository userShippingRepository;

    @Test
    public void removeByIdTest() throws Exception {

        userShippingService.removeById(1L);
        Mockito.verify(userShippingRepository).delete(1L);
    }

    @Test
    public void findByIdTest() throws Exception {
        UserShipping userShipping1 = new UserShipping();

        when(userShippingRepository.findOne(1L)).thenReturn(userShipping1);

        UserShipping userShipping = userShippingService.findById(1L);

          Assert.assertNotNull(userShipping);
        Mockito.verify(userShippingRepository).findOne(1L);
    }
}
