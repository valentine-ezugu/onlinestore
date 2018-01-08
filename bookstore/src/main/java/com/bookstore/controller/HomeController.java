package com.bookstore.controller;

import com.bookstore.domain.*;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.dto.book.BookDetailExtraLite;
import com.bookstore.dto.book.BookDetailForShelf;
import com.bookstore.dto.order.OrderForFindOne;
import com.bookstore.dto.user.*;
import com.bookstore.services.api.*;
import com.bookstore.services.impl.UserSecurityService;
import com.bookstore.utility.MailConstructor;
import com.bookstore.utility.SecurityUtility;
import com.bookstore.utility.USConstants;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.*;


@Controller
public class HomeController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;


    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private UserShippingService userShippingService;

    @Autowired
    private SecurityUtility securityUtility;

    @Autowired
    private Mapper mapper;

    @RequestMapping("/")
    public String index() {
        return "redirect:index";
    }

    @RequestMapping("/index")
    public String home() {
        return "index";
    }


    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "Myaccount";
    }

    @RequestMapping("/hours")
    public String hours() {
        return "hours";
    }

    @RequestMapping("/faq")
    public String faq() {
        return "faq";
    }


    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/bookshelf")
    public String bookshelf(Model model, Principal principal) {

        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            UserForLogin userForLogin = new UserForLogin(user);

            model.addAttribute("user", userForLogin);
        }

        List<Book> bookList = bookService.findAll();
        List<BookDetailForShelf> bookDetailForShelfList = new ArrayList<>();
        for (Book book : bookList) {
            bookDetailForShelfList.add(new BookDetailForShelf(book));
        }
        model.addAttribute("bookList", bookDetailForShelfList);
        model.addAttribute("activeAll", true);

        return "bookshelf";
    }


    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/bookDetail")
    public String bookDetail(
            @PathParam("id") Long id, Model model, Principal principal
    ) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            UserForLogin userForLogin1 = new UserForLogin(user);
            model.addAttribute("user", userForLogin1);
        }

        Book book = bookService.findOne(id);

        BookDetailExtraLite bookDetailExtraLite = new BookDetailExtraLite(book);

        model.addAttribute("book", bookDetailExtraLite);

        List<Integer> qtyList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);

        return "bookDetail";
    }

    @RequestMapping("/ForgetPassword")
    public String ForgetPassword(HttpServletRequest request, @ModelAttribute("email") String email, Model model) {

        model.addAttribute("classActiveForgetPassword", true);

        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("emailNotExits", true);
            return "Myaccount";
        }

        String password = securityUtility.randomPassword();
        String encryptedPassword = securityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        userService.save(user);

        //mostly sending passowrd to email logic
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        SimpleMailMessage newEmail = mailConstructor.constructorResetTokenEmail(appUrl, request.getLocale(), token, user, password);

        mailSender.send(newEmail);
        model.addAttribute("forgetPasswordEmailSent", true);

        return "Myaccount";
    }


    @RequestMapping("/listOfCreditCards")
    public String listOfCreditCards(
            Model model, Principal principal, HttpServletRequest request
    ) {
        User user = userService.findByUsername(principal.getName());

        UserForProfile userForProfile = new UserForProfile(user);

        model.addAttribute("user", userForProfile);
        model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
        model.addAttribute("userShippingList", userForProfile.getUserShippingList());
        model.addAttribute("orderList", userForProfile.getOrderList());

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "Myprofile";
    }

    @RequestMapping("/listOfShippingAddresses")
    public String listOfShippingAddresses(
            Model model, Principal principal, HttpServletRequest request
    ) {
        User user = userService.findByUsername(principal.getName());
        UserForProfile userForProfile = new UserForProfile(user);

        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
        model.addAttribute("userShippingList", userForProfile.getUserShippingList());
        model.addAttribute("orderList", userForProfile.getOrderList());

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);


        return "Myprofile";
    }

    @RequestMapping("addNewCreditCard")
    public String addNewCreditCard(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        UserForProfile userForProfile = new UserForProfile(user);


        model.addAttribute("user", userForProfile);
        model.addAttribute("addNewCreditCard", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);


        UserBilling userBilling = new UserBilling();
        UserPayment userPayment = new UserPayment();

        UserForPaymentOrOrder userForBillingAddresses = new UserForPaymentOrOrder(userBilling);

        UserForPaymentInfo userForPaymentAddresses = mapper.map(userPayment, UserForPaymentInfo.class, "userForPaymentInfo");

        model.addAttribute("userBilling", userForBillingAddresses);
        model.addAttribute("userPayment", userForPaymentAddresses);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
        model.addAttribute("userShippingList", userForProfile.getUserShippingList());
        model.addAttribute("orderList", userForProfile.getOrderList());
        return "Myprofile";
    }

    @RequestMapping("addNewShippingAddesses")
    public String addNewShippingAddesses(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        UserForProfile userForProfile = new UserForProfile(user);

        model.addAttribute("user", userForProfile);

        model.addAttribute("addNewShippingAddress", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);

        UserShipping userShipping = new UserShipping();


        UserForShippingLite userForShippingLite = mapper.map(userShipping, UserForShippingLite.class, "userShippingLiteId");

        model.addAttribute("userShipping", userForShippingLite);


        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
        model.addAttribute("userShippingList", userForProfile.getUserShippingList());

        model.addAttribute("orderList", userForProfile.getOrderList());

        return "Myprofile";
    }

    @RequestMapping(value = "/addNewShippingAddress", method = RequestMethod.POST)
    public String addNewShippingAddressPost(
            @ModelAttribute("userShipping") UserShipping userShipping,
            Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.updateUserShipping(userShipping, user);
        UserForProfile userForProfile = new UserForProfile(user);

        model.addAttribute("user", userForProfile);
        model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
        model.addAttribute("userShippingList", userForProfile.getUserShippingList());
        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("orderList", userForProfile.getOrderList());

        return "Myprofile";
    }


    @RequestMapping(value = "/addNewCreditCard", method = RequestMethod.POST)
    public String addNewCreditCard(@ModelAttribute("userPayment") UserPayment userPayment,
                                   @ModelAttribute("userBilling") UserBilling userBilling, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        UserForProfile userForProfile = new UserForProfile(user);

        userService.updateUserBilling(userBilling, userPayment, user);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("user", userForProfile);
        model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
        model.addAttribute("userShippingList", userForProfile.getUserShippingList());

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("orderList", userForProfile.getOrderList());

        return "Myprofile";
    }

    @RequestMapping("/removeCreditCard")
    public String removeCreditCard(
            @ModelAttribute("id") Long creditCardId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());

        UserForProfile userForProfile = new UserForProfile(user);

        UserPayment userPayment = userPaymentService.findById(creditCardId);

        UserForPaymentInfo userForPaymentAddresses = mapper.map(userPayment, UserForPaymentInfo.class, "userForPaymentInfo");


        if (user.getId() != userPayment.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", userForProfile);
            userPaymentService.removeById(creditCardId);
            model.addAttribute("userPayment", userForPaymentAddresses);

            model.addAttribute("listOfCreditCards", true);
            model.addAttribute("classActiveBilling", true);
            model.addAttribute("listOfShippingAddresses", true);

            model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
            model.addAttribute("userShippingList", userForProfile.getUserShippingList());

            return "Myprofile";
        }
    }

    @RequestMapping(value = "/setDefaultPayment", method = RequestMethod.POST)
    public String setDefaultPayment(
            @ModelAttribute("defaultUserPaymentId") Long defaultPaymentId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.setUserDefaultPayment(defaultPaymentId, user);

        UserForProfile userForProfile = new UserForProfile(user);

        model.addAttribute("user", userForProfile);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
        model.addAttribute("userShippingList", userForProfile.getUserShippingList());
        model.addAttribute("orderList", userForProfile.getOrderList());

        return "Myprofile";
    }

    @RequestMapping(value = "/setDefaultShippingAddress", method = RequestMethod.POST)
    public String setDefaultShippingAddress(
            @ModelAttribute("defaultShippingAddressId") Long defaultShippingId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.setUserDefaultShipping(defaultShippingId, user);

        UserForProfile userForProfile = new UserForProfile(user);

        model.addAttribute("user", userForProfile);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
        model.addAttribute("userShippingList", userForProfile.getUserShippingList());
        model.addAttribute("orderList", userForProfile.getOrderList());

        return "Myprofile";
    }

    @RequestMapping("/updateCreditCard")
    public String updateCreditCard(
            @ModelAttribute("id") Long creditCardId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserPayment userPayment = userPaymentService.findById(creditCardId);

        UserForProfile userForProfile = new UserForProfile(user);

        if (user.getId() != userPayment.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", userForProfile);

            UserBilling userBilling = userPayment.getUserBilling();

            model.addAttribute("userPayment", userPayment);
            model.addAttribute("userBilling", userBilling);

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);

            model.addAttribute("stateList", stateList);
            model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
            model.addAttribute("userShippingList", userForProfile.getUserShippingList());

            model.addAttribute("addNewCreditCard", true);
            model.addAttribute("classActiveBilling", true);
            model.addAttribute("listOfShippingAddresses", true);

            return "Myprofile";
        }

    }


    @RequestMapping("/updateUserShipping")
    public String updateUserShipping(
            @ModelAttribute("id") Long shippingAddressId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(shippingAddressId);

        UserForProfile userForProfile = new UserForProfile(user);

        UserForShippingLite userForShippingLite = mapper.map(userShipping, UserForShippingLite.class, "userShippingLiteId");

        if (user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", userForProfile);

            model.addAttribute("userShipping", userForShippingLite);

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            model.addAttribute("addNewShippingAddress", true);
            model.addAttribute("classActiveShipping", true);
            model.addAttribute("listOfCreditCards", true);

            model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
            model.addAttribute("userShippingList", userForProfile.getUserShippingList());
            return "Myprofile";
        }

    }

    @RequestMapping("/removeUserShipping")
    public String removeUserShipping(
            @ModelAttribute("id") Long userShippingId, Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());

        UserShipping userShipping = userShippingService.findById(userShippingId);

        UserForProfile userForProfile = new UserForProfile(user);

        if (user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", userForProfile);

            userShippingService.removeById(userShippingId);

            model.addAttribute("listOfShippingAddresses", true);
            model.addAttribute("classActiveShipping", true);

            model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
            model.addAttribute("userShippingList", userForProfile.getUserShippingList());
            model.addAttribute("orderList", userForProfile.getOrderList());

            return "Myprofile";
        }
    }

    @RequestMapping("/Myprofile")
    public String Myprofile(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        UserForProfile userForProfile = new UserForProfile(user);


        model.addAttribute("user", userForProfile);
        model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
        model.addAttribute("userShippingList", userForProfile.getUserShippingList());
        model.addAttribute("orderList", userForProfile.getOrderList());

        UserShipping userShipping = new UserShipping();


        UserForShippingLite userForShippingLite = mapper.map(userShipping, UserForShippingLite.class, "userShippingLiteId");

        model.addAttribute("userShipping", userForShippingLite);

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("listOfShippingAddresses", true);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);
        model.addAttribute("classActiveEdit", true);

        return "Myprofile";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String newUSerPost(
            HttpServletRequest request,
            @ModelAttribute("email") String userEmail,
            @ModelAttribute("username") String username,
            Model model)
            throws Exception {
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", username);

        if (userService.findByUsername(username) != null) {

            model.addAttribute("usernameExits", true);

            return "Myaccount";
        }

        if (userService.findByEmail(userEmail) != null) {
            model.addAttribute("emailExits", true);

            return "Myaccount";
        }


        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);

        String password = securityUtility.randomPassword();

        String encryptedPassword = securityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        //mostly sending passowrd to email logic
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        SimpleMailMessage email = mailConstructor.constructorResetTokenEmail(appUrl, request.getLocale(), token, user, password);

        mailSender.send(email);
        model.addAttribute("emailsent", true);

        return "Myaccount";
    }


    @RequestMapping("/newUser")
    public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
        PasswordResetToken passToken = userService.getPasswordResetToken(token);

        if (passToken == null) {
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }

        User user = passToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user", user);

        model.addAttribute("classActiveEdit", true);

        return "Myprofile";
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(
            @ModelAttribute("user") User user,
            @ModelAttribute("newPassword") String newPassword,
            Model model
    ) throws Exception {
        User currentUser = userService.findById(user.getId());

        if (currentUser == null) {
            throw new Exception("User not found");
        }


        if (userService.findByEmail(user.getEmail()) != null) {
            if (userService.findByEmail(user.getEmail()).getId() != currentUser.getId()) {
                model.addAttribute("emailExists", true);
                return "Myprofile";
            }
        }


        if (userService.findByUsername(user.getUsername()) != null) {
            if (userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
                model.addAttribute("usernameExists", true);
                return "Myprofile";
            }
        }


        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
            BCryptPasswordEncoder passwordEncoder = securityUtility.passwordEncoder();
            String dbPassword = currentUser.getPassword();
            if (passwordEncoder.matches(user.getPassword(), dbPassword)) {
                currentUser.setPassword(passwordEncoder.encode(newPassword));
            } else {
                model.addAttribute("incorrectPassword", true);

                return "Myprofile";
            }
        }

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());

        userService.save(currentUser);

        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUser);
        model.addAttribute("classActiveEdit", true);

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("listOfCreditCards", true);


        UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("orderList", user.getOrderList());

        return "Myprofile";
    }

    @RequestMapping("/orderDetail")
    public String orderDetail(
            @RequestParam("id") Long orderId,
            Principal principal, Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        UserForProfile userForProfile = new UserForProfile(user);

        Order order = orderService.findOne(orderId);

        OrderForFindOne orderForFindOne = new OrderForFindOne(order);

        if (order.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            List<CartItem> cartItemList = cartItemService.findByOrder(order);
            model.addAttribute("cartItemList", cartItemList);
            model.addAttribute("user", userForProfile);
            model.addAttribute("order", orderForFindOne);

            model.addAttribute("userPaymentList", userForProfile.getUserPaymentList());
            model.addAttribute("userShippingList", userForProfile.getUserShippingList());
            model.addAttribute("orderList", userForProfile.getOrderList());

            UserShipping userShipping = new UserShipping();

            List<String> list = new ArrayList<>();
            list.add("mapping.xml");

            mapper = new DozerBeanMapper(list);
            UserForShippingLite userForShippingLite = mapper.map(userShipping, UserForShippingLite.class, "userShippingLiteId");

            model.addAttribute("userShipping", userForShippingLite);

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            model.addAttribute("listOfShippingAddresses", true);
            model.addAttribute("classActiveOrders", true);
            model.addAttribute("listOfCreditCards", true);
            model.addAttribute("displayOrderDetail", true);

            return "Myprofile";
        }
    }
}
