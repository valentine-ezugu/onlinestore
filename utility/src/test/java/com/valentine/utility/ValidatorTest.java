package com.valentine.utility;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Validator.class)
public class ValidatorTest {

    String mail = "valentineezugu@yahoo.com";
    String wrongEmailPattern =  "@valetine.yahoo.c";

    @Test
    public void emailTest() throws Exception {

        Assert.assertTrue(Validator.EMAIL_PATTERN.matcher(mail).matches());
        Assert.assertFalse(Validator.EMAIL_PATTERN.matcher(wrongEmailPattern).matches());
    }

}
