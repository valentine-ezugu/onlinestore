package com.valentine.service;

import com.valentine.domain.BillingAddress;
import com.valentine.domain.UserBilling;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingAddressServiceImpl.class)
public class BillingAddressServiceTest {

    @Autowired
    private BillingAddressService billingAddressService;

    @Test
    public void setByUserBillingTest(){

        BillingAddress billingAddress = new BillingAddress();

        UserBilling userBilling = new UserBilling();
        userBilling.setUserBillingName("val");
        userBilling.setUserBillingStreet1("mos");
        userBilling.setUserBillingStreet2("mos1");
        userBilling.setUserBillingCity("brest");
        userBilling.setUserBillingState("brest");
        userBilling.setUserBillingCountry("bel");
        userBilling.setUserBillingZipCode("23232");

        billingAddress.setBillingAddressName(userBilling.getUserBillingName());
        billingAddress.setBillingAddressStreet1(userBilling.getUserBillingStreet1());
        billingAddress.setBillingAddressStreet2(userBilling.getUserBillingStreet2());
        billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
        billingAddress.setBillingAddressState(userBilling.getUserBillingState());
        billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
        billingAddress.setBillingAddressZipCode(userBilling.getUserBillingZipCode());

      BillingAddress billingAddress1 =  billingAddressService.setByUserBilling(userBilling, billingAddress);

        Assert.assertNotNull("billing address should not be null " , billingAddress1);
        Assert.assertEquals(billingAddress, billingAddress1);
        Assert.assertNotNull("val", billingAddress.getBillingAddressName());

    }

}
