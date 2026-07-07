package com.food_order.backend.services.implementation;

import com.food_order.backend.Repositories.CartItemRepository;
import com.food_order.backend.Repositories.CartRepository;
import com.food_order.backend.Repositories.ItemRepository;
import com.food_order.backend.Repositories.UserRepository;
import com.food_order.backend.dto.AddItemToCartRequestDto;
import com.food_order.backend.enities.Cart;
import com.food_order.backend.enities.CartItem;
import com.food_order.backend.enities.Item;
import com.food_order.backend.enities.User;
import com.food_order.backend.services.CartItemService;
import com.food_order.backend.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    public final ItemRepository itemRepository;
    public final UserRepository userRepository;
    public final CartRepository cartRepository;
    public final CartItemRepository cartItemRepository;

    @Override
//    public void addToCart(AddItemToCartRequestDto addItemToCartRequestDto){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//
//        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
//
//        Cart cart = cartRepository.getByUser(user).orElse(Cart.builder().user(user).cartItems(null).build());
//
//        Item isItemExist = itemRepository.findById(addItemToCartRequestDto.getItemId()).orElseThrow(()-> new RuntimeException("Item Not Found to add in the cart"));
//
//        CartItem cartItem = CartItem.builder().cart(cart).quantity(Long.valueOf(addItemToCartRequestDto.getQuantity())).build();
//
//        CartItem savedCartItem =  cartItemRepository.save(cartItem);
//        cart.getCartItems().add(savedCartItem);
//
//    }

    public void addToCart(AddItemToCartRequestDto addItemToCartRequestDto) {

        // 1. Get current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 2. Fetch or Create & Save the Cart immediately to prevent transient errors
        Cart cart = cartRepository.getByUser(user).orElseGet(() -> {
            Cart newCart = Cart.builder()
                    .user(user)
                    .cartItems(new ArrayList<>()) // 💡 Use an empty list, NEVER null
                    .build();
            return cartRepository.save(newCart); // 💡 Save it to the DB right away
        });

        // 3. Find the item to be added
        Item item = itemRepository.findById(addItemToCartRequestDto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item Not Found to add in the cart"));

        // 4. Create the new CartItem (Make sure to map the 'item' field too!)
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item) // 💡 Don't forget to attach the item itself!
                .quantity(Long.valueOf(addItemToCartRequestDto.getQuantity()))
                .build();

        // 5. Save the child item
        CartItem savedCartItem = cartItemRepository.save(cartItem);

        // 6. Link to parent cart and save the parent cart
        cart.getCartItems().add(savedCartItem);
        cartRepository.save(cart); // 💡 Persist the relationship changes
    }


}
