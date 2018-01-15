package com.valentine.bookstore.rest.controller;

import com.valentine.bookstore.service.*;
import com.valentine.common.utility.MailConstructor;
import com.valentine.common.utility.USConstants;
import com.valentine.domain.*;
import com.valentine.dto.billingAddress.BillingAddressCheckOut;
import com.valentine.dto.cart.CartItemForList;
import com.valentine.dto.payment.PaymentExtraInfo;
import com.valentine.dto.shipping.ShippingAddressInfo;
import com.valentine.dto.user.UserForPaymentInfo;
import com.valentine.dto.user.UserForProfile;
import com.valentine.dto.user.UserForShippingLite;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller
public class CheckoutController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private BillingAddressService billingAddressService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserShippingService userShippingService;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private Mapper mapper;

    private ShippingAddress shippingAddress = new ShippingAddress();

    private BillingAddress billingAddress = new BillingAddress();

    private Payment payment = new Payment();

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/checkout")
    public String checkout(@RequestParam("id") Long cartId,
                           @RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField, Model model,
                           Principal principal) {

        User user = userService.findByUsername(principal.getName());

        if (cartId != user.getShoppingCart().getId()) {
            return "badRequestPage";
        }

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

        if (cartItemList.size() == 0) {
            model.addAttribute("emptyCart", true);
            return "forward:/shoppingCart/cart";
        }

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getBook().getInStockNumber() < cartItem.getQty()) {
                model.addAttribute("notEnoughStock", true);
                return "forward:/shoppingCart/cart";
            }

        }
        List<CartItemForList> cartItemForLists = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            cartItemForLists.add(mapper.map(cartItem, CartItemForList.class, "cartItemForList"));
        }


        List<UserShipping> userShippingList = user.getUserShippingList();

        List<UserForShippingLite> userForShippingLites = new ArrayList<>();

        for (UserShipping userShipping : userShippingList) {
            userForShippingLites.add(mapper.map(userShipping, UserForShippingLite.class, "userShippingLiteId"));
        }

        List<UserPayment> userPaymentList = user.getUserPaymentList();
        List<UserForPaymentInfo> userForPaymentInfoList = new ArrayList<>();

        for (UserPayment userPayment : userPaymentList) {
            userForPaymentInfoList.add(mapper.map(userPayment, UserForPaymentInfo.class, "userForPaymentInfo"));
        }

        model.addAttribute("userShippingList", userForShippingLites);
        model.addAttribute("userPaymentList", userForPaymentInfoList);

        if (userPaymentList.size() == 0) {
            model.addAttribute("emptyPaymentList", true);
        } else {
            model.addAttribute("emptyPaymentList", false);
        }

        if (userShippingList.size() == 0) {
            model.addAttribute("emptyShippingList", true);
        } else {
            model.addAttribute("emptyShippingList", false);
        }


        for (UserShipping userShipping : userShippingList) {
            if (userShipping.isUserShippingDefault()) {
                shippingAddressService.setByUserShipping(userShipping, shippingAddress);
            }
        }


        for (UserPayment userPayment : userPaymentList) {
            if (userPayment.isDefaultPayment()) {
                paymentService.setByUserPayment(userPayment, payment);
                billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
            }
        }
        UserForProfile userForProfile = mapper.map(user, UserForProfile.class, "userForProfile");

        PaymentExtraInfo paymentExtraInfo = mapper.map(payment, PaymentExtraInfo.class, "paymentExtraInfo");
        BillingAddressCheckOut billingAddressCheckOut = mapper.map(billingAddress, BillingAddressCheckOut.class, "billingAddressCheckout");
        ShippingAddressInfo shippingAddressInfo = mapper.map(shippingAddress, ShippingAddressInfo.class, "shippingAddressId");

        model.addAttribute("shippingAddress", shippingAddressInfo);
        model.addAttribute("payment", paymentExtraInfo);
        model.addAttribute("billingAddress", billingAddressCheckOut);
        model.addAttribute("cartItemList", cartItemForLists);
        model.addAttribute("shoppingCart", userForProfile.getShoppingCart());

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("classActiveShipping", true);

        if (missingRequiredField) {
            model.addAttribute("missingRequiredField", true);
        }

        return "checkout";

    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/setShippingAddress")
    public String setShippingAddress(@RequestParam("userShippingId") Long userShippingId, Principal principal,
                                     Model model) {
        User user = userService.findByUsername(principal.getName());

        UserShipping userShipping = userShippingService.findById(userShippingId);

        UserForProfile userForProfile = mapper.map(user, UserForProfile.class, "userForProfile");

        if (userShipping.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            shippingAddressService.setByUserShipping(userShipping, shippingAddress);

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            List<CartItemForList> cartItemForLists = new ArrayList<>();
            for (CartItem cartItem : cartItemList) {
                cartItemForLists.add(mapper.map(cartItem, CartItemForList.class, "cartItemForList"));
            }
            PaymentExtraInfo paymentExtraInfo = mapper.map(payment, PaymentExtraInfo.class, "paymentExtraInfo");
            BillingAddressCheckOut billingAddressCheckOut = mapper.map(billingAddress, BillingAddressCheckOut.class, "billingAddressCheckout");
            ShippingAddressInfo shippingAddressInfo = mapper.map(shippingAddress, ShippingAddressInfo.class, "shippingAddressId");

            model.addAttribute("shippingAddress", shippingAddressInfo);
            model.addAttribute("payment", paymentExtraInfo);
            model.addAttribute("billingAddress", billingAddressCheckOut);
            model.addAttribute("cartItemList", cartItemForLists);
            model.addAttribute("shoppingCart", userForProfile.getShoppingCart());

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            List<UserForShippingLite> userForShippingLites = new ArrayList<>();
            List<UserShipping> userShippingList = user.getUserShippingList();

            List<UserPayment> userPaymentList = user.getUserPaymentList();


            for (UserShipping userShipping1 : userShippingList) {
                userForShippingLites.add(mapper.map(userShipping1, UserForShippingLite.class, "userShippingLiteId"));
            }


            List<UserForPaymentInfo> userForPaymentInfoList = new ArrayList<>();

            for (UserPayment userPayment : userPaymentList) {
                userForPaymentInfoList.add(mapper.map(userPayment, UserForPaymentInfo.class, "userForPaymentInfo"));
            }
            model.addAttribute("userShippingList", userForShippingLites);
            model.addAttribute("userPaymentList", userForPaymentInfoList);

            model.addAttribute("shippingAddress", shippingAddressInfo);

            model.addAttribute("classActiveShipping", true);

            if (userPaymentList.size() == 0) {
                model.addAttribute("emptyPaymentList", true);
            } else {
                model.addAttribute("emptyPaymentList", false);
            }

            model.addAttribute("emptyShippingList", false);

            return "checkout";
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/setPaymentMethod")
    public String setPaymentMethod(@RequestParam("userPaymentId") Long userPaymentId, Principal principal,
                                   Model model) {
        User user = userService.findByUsername(principal.getName());
        UserPayment userPayment = userPaymentService.findById(userPaymentId);
        UserBilling userBilling = userPayment.getUserBilling();

        UserForProfile userForProfile = mapper.map(user, UserForProfile.class, "userForProfile");


        if (userPayment.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            paymentService.setByUserPayment(userPayment, payment);

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            billingAddressService.setByUserBilling(userBilling, billingAddress);

            List<CartItemForList> cartItemForLists = new ArrayList<>();
            for (CartItem cartItem : cartItemList) {

                cartItemForLists.add(mapper.map(cartItem, CartItemForList.class, "cartItemForList"));
            }
            ShippingAddressInfo shippingAddressInfo = mapper.map(shippingAddress, ShippingAddressInfo.class, "shippingAddressId");

            BillingAddressCheckOut billingAddressCheckOut = mapper.map(billingAddress, BillingAddressCheckOut.class, "billingAddressCheckout");
            PaymentExtraInfo paymentExtraInfo = mapper.map(payment, PaymentExtraInfo.class, "paymentExtraInfo");
            model.addAttribute("shippingAddress", shippingAddressInfo);
            model.addAttribute("payment", paymentExtraInfo);
            model.addAttribute("billingAddress", billingAddressCheckOut);
            model.addAttribute("cartItemList", cartItemForLists);
            model.addAttribute("shoppingCart", userForProfile.getShoppingCart());

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            List<UserShipping> userShippingList = user.getUserShippingList();

            List<UserForShippingLite> userForShippingLites = new ArrayList<>();
            List<UserForPaymentInfo> userForPaymentInfoList = new ArrayList<>();


            for (UserShipping userShipping : userShippingList) {
                userForShippingLites.add(mapper.map(userShipping, UserForShippingLite.class, "userShippingLiteId"));
            }

            List<UserPayment> userPaymentList = user.getUserPaymentList();

            for (UserPayment userPayment1 : userPaymentList) {
                userForPaymentInfoList.add(mapper.map(userPayment1, UserForPaymentInfo.class, "userForPaymentInfo"));
            }

            model.addAttribute("userShippingList", userForShippingLites);
            model.addAttribute("userPaymentList", userForPaymentInfoList);

            model.addAttribute("shippingAddress", shippingAddressInfo);

            model.addAttribute("classActivePayment", true);

            model.addAttribute("emptyPaymentList", false);

            if (userShippingList.size() == 0) {
                model.addAttribute("emptyShippingList", true);
            } else {
                model.addAttribute("emptyShippingList", false);
            }

            return "checkout";
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkoutPost(@ModelAttribute("shippingAddress") ShippingAddressInfo shippingAddressInfo,
                               @ModelAttribute("billingAddress") BillingAddressCheckOut billingAddressCheckOut,
                               @ModelAttribute("payment") PaymentExtraInfo paymentExtraInfo,
                               @ModelAttribute("billingSameAsShipping") String billingSameAsShipping,
                               @ModelAttribute("shippingMethod") String shippingMethod, Principal principal, Model model) {
        ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();


        billingAddressCheckOut = mapper.map(billingAddress, BillingAddressCheckOut.class, "billingAddressCheckout");

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        model.addAttribute("cartItemList", cartItemList);

        if (billingSameAsShipping.equals("true")) {
            billingAddressCheckOut.setBillingAddressName(shippingAddressInfo.getShippingAddressName());
            billingAddressCheckOut.setBillingAddressStreet1(shippingAddressInfo.getShippingAddressStreet1());
            billingAddressCheckOut.setBillingAddressStreet2(shippingAddressInfo.getShippingAddressStreet2());
            billingAddressCheckOut.setBillingAddressCity(shippingAddressInfo.getShippingAddressCity());
            billingAddressCheckOut.setBillingAddressState(shippingAddressInfo.getShippingAddressState());
            billingAddressCheckOut.setBillingAddressCountry(shippingAddressInfo.getShippingAddressCountry());
            billingAddressCheckOut.setBillingAddressZipCode(shippingAddressInfo.getShippingAddressZipCode());
        }

        if (shippingAddressInfo.getShippingAddressStreet1().isEmpty()
                || shippingAddressInfo.getShippingAddressCity().isEmpty()
                || shippingAddressInfo.getShippingAddressState().isEmpty()
                || shippingAddressInfo.getShippingAddressName().isEmpty()
                || shippingAddressInfo.getShippingAddressZipCode().isEmpty()
                || paymentExtraInfo.getCardNumber().isEmpty()
                || paymentExtraInfo.getCvc() == 0 || billingAddressCheckOut.getBillingAddressStreet1().isEmpty()
                || billingAddressCheckOut.getBillingAddressCity().isEmpty()
                || billingAddressCheckOut.getBillingAddressState().isEmpty()
                || billingAddressCheckOut.getBillingAddressName().isEmpty()
                || billingAddressCheckOut.getBillingAddressZipCode().isEmpty())
            return "redirect:/checkout?id=" + shoppingCart.getId() + "&missingRequiredField=true";

        User user = userService.findByUsername(principal.getName());

        Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);

        mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));

        shoppingCartService.clearShoppingCart(shoppingCart);

        LocalDate today = LocalDate.now();
        LocalDate estimatedDeliveryDate;

        if (shippingMethod.equals("groundShipping")) {
            estimatedDeliveryDate = today.plusDays(5);
        } else {
            estimatedDeliveryDate = today.plusDays(3);
        }

        model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);

        return "orderSubmittedPage";
    }


}
