package com.valentine.utility;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityUtility.class)
public class SecurityUtilityTest {

    @MockBean
    private Random random;

    @Test
    public void createRandomPasswordSeed() {
        random = new Random(1);
        String randomPassword = new SecurityUtility(random).randomPassword();
        Assert.assertEquals("seed = 1", "83T81EOKK3ZZB2S9U5", randomPassword);
    }

}
