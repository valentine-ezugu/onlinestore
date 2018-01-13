package com.rest_end.rest;

import com.domain.domain.Book;
import com.domain.domain.CartItem;
import com.domain.domain.ShoppingCart;
import com.domain.domain.User;
import com.domain.dto.cart.CartItemForList;
import com.domain.dto.shoppingCart.ShoppingCartLite;
import com.services.api.BookService;
import com.services.api.CartItemService;
import com.services.api.ShoppingCartService;
import com.services.api.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private Mapper mapper;

    @RequestMapping(value = "/cart")
    public String shoppingCart(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        ShoppingCart shoppingCart = user.getShoppingCart();


        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        List<CartItemForList> cartItemForLists = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            cartItemForLists.add(mapper.map(cartItem, CartItemForList.class, "cartItemForList"));
        }

        ShoppingCartLite shoppingCartLite = new ShoppingCartLite(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);

        model.addAttribute("cartItemList", cartItemForLists);
        model.addAttribute("shoppingCart", shoppingCartLite);

        return "shoppingCart";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/addItem")
    public String addItem(
            @ModelAttribute("book") Book book,
            @ModelAttribute("qty") String qty,
            Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());


        try {
            book = bookService.findOne(book.getId());

            if (Integer.parseInt(qty) > book.getInStockNumber()) {
                model.addAttribute("notEnoughStock", true);
                return "forward:/bookDetail?id=" + book.getId();
            }

            CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
            model.addAttribute("addBookSuccess", true);

        } catch (NullPointerException e) {

        }

        return "forward:/bookDetail?id=" + book.getId();
    }


    @RequestMapping("/updateCartItem/")
    public String updateShoppingCartItem(@ModelAttribute("id") Long cartItemId,
                                         @ModelAttribute("qty") int qty) {

        CartItem cartItem = cartItemService.findById(cartItemId);
        cartItem.setQty(qty);
        cartItemService.updateCartItem(cartItem);
        return "forward:/shoppingCart/cart";
    }

    @RequestMapping("/removeItem")
    public String updateShoppingCartItem(@ModelAttribute("id") Long id) {
        cartItemService.removeCartItem(cartItemService.findById(id));
        return "forward:/shoppingCart/cart";
    }
}
