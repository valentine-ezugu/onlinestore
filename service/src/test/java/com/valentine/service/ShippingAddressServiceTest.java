package com.valentine.service;

import com.valentine.domain.ShippingAddress;
import com.valentine.domain.UserShipping;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShippingAddressServiceImpl.class)
public class ShippingAddressServiceTest {

    @Autowired
    private ShippingAddressService shippingAddressService;


    @Test
    public void setByUserShipping () throws Exception {
        UserShipping userShipping = new UserShipping();
        userShipping.setId(1L);
        userShipping.setUserShippingCity("BREST");
        userShipping.setUserShippingState("Brest St");
        userShipping.setUserShippingCountry("Belarus");

        ShippingAddress shippingAddress  = new ShippingAddress();

        shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
        shippingAddress.setShippingAddressStreet1(userShipping.getUserShippingStreet1());
        shippingAddress.setShippingAddressStreet2(userShipping.getUserShippingStreet2());
        shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
        shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
        shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
        shippingAddress.setShippingAddressZipCode(userShipping.getUserShippingZipCode());

        ShippingAddress newShippingAddress = shippingAddressService.setByUserShipping(userShipping,shippingAddress);

        Assert.assertNotNull(newShippingAddress);
        Assert.assertEquals("BREST", newShippingAddress.getShippingAddressCity() );
        Assert.assertEquals("Brest St", newShippingAddress.getShippingAddressState() );
        Assert.assertEquals("Belarus", newShippingAddress.getShippingAddressCountry() );
    }

}


