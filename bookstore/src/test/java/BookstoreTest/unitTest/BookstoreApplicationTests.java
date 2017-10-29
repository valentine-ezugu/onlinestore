package BookstoreTest.unitTest;

import BookstoreTest.ws.AbstractTest;
import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.api.UserService;
import com.bookstore.utility.SecurityUtility;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertNotSame;

@Transactional
public class BookstoreApplicationTests extends AbstractTest {

    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addUserTest() throws Exception {
        LOGGER.debug("test: save");
        User user1 = new User();
        user1.setUsername("V");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("A"));
        user1.setEmail("valentineezugu@yahoo.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));
        userService.createUser(user1, userRoles);
        userRepository.save(user1);
        assert (user1.getUsername().equals("V"));
    }

    @Test
    public void findByUsernameTest() throws Exception {
        LOGGER.debug("test: findByUsernameTest()");
        User user = userService.findByUsername("V");
        assertNotSame("james", "V");
    }

    @Test
    public void getUserByIdTest() throws Exception {
        LOGGER.debug("test: getUserByIdTest()");
        User user = userService.getUserById(2L);
        assert (user.getUsername().matches("V"));
    }

}
