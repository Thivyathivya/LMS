package com.LMS.userManagement.service;

import com.LMS.userManagement.model.Cart;
import com.LMS.userManagement.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
