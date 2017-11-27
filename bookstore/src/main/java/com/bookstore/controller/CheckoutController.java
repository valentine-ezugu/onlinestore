package com.bookstore.controller;

import com.bookstore.domain.*;
import com.bookstore.dto.billingAddress.BillingAddressCheckOut;
import com.bookstore.dto.cart.CartItemForList;
import com.bookstore.dto.payment.PaymentExtraInfo;
import com.bookstore.dto.shipping.ShippingAddressInfo;
import com.bookstore.dto.user.UserForPaymentInfo;
import com.bookstore.dto.user.UserForProfile;
import com.bookstore.dto.user.UserForShippingLite;
import com.bookstore.service.api.*;
import com.bookstore.utility.MailConstructor;
import com.bookstore.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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

    private ShippingAddress shippingAddress = new ShippingAddress();

    private BillingAddress billingAddress = new BillingAddress();

    private Payment payment = new Payment();

    private BillingAddressCheckOut billingAddressCheckOut = new BillingAddressCheckOut(billingAddress);

    private PaymentExtraInfo paymentExtraInfo = new PaymentExtraInfo(payment);

    private ShippingAddressInfo shippingAddressInfo = new ShippingAddressInfo(shippingAddress);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private UserService userService;

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
    private UserShippingService userShippingService;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private OrderService orderService;

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
            cartItemForLists.add( new CartItemForList(cartItem));
            }

        //Dto for usershipping
        List<UserShipping> userShippingList = user.getUserShippingList();

        List<UserForShippingLite> userForShippingLites = new ArrayList<>();

        for(UserShipping userShipping : userShippingList){
            userForShippingLites.add(new UserForShippingLite(userShipping));
        }

        //Dto for userpayment
        List<UserPayment> userPaymentList = user.getUserPaymentList();
        List<UserForPaymentInfo> userForPaymentInfoList = new ArrayList<>();

        for(UserPayment userPayment : userPaymentList){
            userForPaymentInfoList.add(new UserForPaymentInfo(userPayment));
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

        ShoppingCart shoppingCart = user.getShoppingCart();

        for (UserShipping userShipping : userShippingList){
            if (userShipping.isUserShippingDefault()){
                shippingAddressService.setByUserShipping(userShipping, shippingAddress);
            }
        }


        for (UserPayment userPayment : userPaymentList) {
            if (userPayment.isDefaultPayment()) {
                paymentService.setByUserPayment(userPayment, payment);
                billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
            }
        }
        UserForProfile userForProfile = new UserForProfile(user);


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

    @RequestMapping("/setShippingAddress")
    public String setShippingAddress(@RequestParam("userShippingId") Long userShippingId, Principal principal,
                                     Model model) {
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(userShippingId);

        UserForProfile userForProfile = new UserForProfile(user);

        if (userShipping.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            shippingAddressService.setByUserShipping(userShipping, shippingAddress);

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            List<CartItemForList> cartItemForLists = new ArrayList<>();
            for (CartItem cartItem : cartItemList) {
                cartItemForLists.add( new CartItemForList(cartItem));
            }
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

            for(UserShipping userShipping1 : userShippingList){
                userForShippingLites.add(new UserForShippingLite(userShipping));
            }


            List<UserForPaymentInfo> userForPaymentInfoList = new ArrayList<>();

            for(UserPayment userPayment : userPaymentList){
                userForPaymentInfoList.add(new UserForPaymentInfo(userPayment));
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
    @RequestMapping("/setPaymentMethod")
    public String setPaymentMethod(@RequestParam("userPaymentId") Long userPaymentId, Principal principal,
                                   Model model) {
        User user = userService.findByUsername(principal.getName());
        UserPayment userPayment = userPaymentService.findById(userPaymentId);
        UserBilling userBilling = userPayment.getUserBilling();

        UserForProfile userForProfile = new UserForProfile(user);

        if (userPayment.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            paymentService.setByUserPayment(userPayment, payment);

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            billingAddressService.setByUserBilling(userBilling, billingAddress);

            List<CartItemForList> cartItemForLists = new ArrayList<>();
            for (CartItem cartItem : cartItemList) {
                cartItemForLists.add( new CartItemForList(cartItem));
            }

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
            //Dto added
            for( UserShipping userShipping : userShippingList){
                userForShippingLites.add(new UserForShippingLite(userShipping));
            }

            List<UserPayment> userPaymentList = user.getUserPaymentList();

            for( UserPayment userPayment1 : userPaymentList){
                userForPaymentInfoList.add(new UserForPaymentInfo(userPayment1));
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

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkoutPost(@ModelAttribute("shippingAddress") ShippingAddressInfo shippingAddressInfo,
                               @ModelAttribute("billingAddress")BillingAddressCheckOut billingAddressCheckOut,
                               @ModelAttribute("payment") PaymentExtraInfo paymentExtraInfo,
                               @ModelAttribute("billingSameAsShipping") String billingSameAsShipping,
                               @ModelAttribute("shippingMethod") String shippingMethod, Principal principal, Model model) {
        ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();

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
