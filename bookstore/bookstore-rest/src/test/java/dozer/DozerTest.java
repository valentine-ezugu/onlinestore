package dozer;

import com.valentine.bookstore.rest.config.MappingConfig;
import com.valentine.domain.UserShipping;
import com.valentine.dto.user.UserForShippingLite;
import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MappingConfig.class})
public class DozerTest {

    @Autowired
    private Mapper mapper;

    @Test
    public void dozerTest() throws Exception {

        UserShipping userShipping = new UserShipping();
        userShipping.setUserShippingName("Chiko");
        UserForShippingLite userForShippingLite = mapper.map(userShipping, UserForShippingLite.class, "userShippingLiteId");
        Assert.assertEquals(userShipping.getUserShippingName(), userForShippingLite.getUserShippingName());
    }

}
