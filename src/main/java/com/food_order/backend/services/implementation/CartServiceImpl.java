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
    public void addToCart(AddItemToCartRequestDto addItemToCartRequestDto) {

        // STEP 1: Get the currently logged-in user's details
        // SecurityContextHolder holds the details of who is logged into the application right now.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Extract the email of the logged-in user from the authentication object
        String email = authentication.getName();
        
        // Search our database to find the full User object that matches this email
        User user = userRepository.findByEmail(email)
                // If we can't find a user with this email, we throw an error immediately
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // STEP 2: Find the user's cart or create a new one if they don't have it yet
        // We look for a cart that belongs to this specific user.
        Cart cart = cartRepository.getByUser(user).orElseGet(() -> {
            // If they don't have a cart, we build a brand new empty cart for them
            Cart newCart = Cart.builder()
                    .user(user) // Assign the cart to our logged-in user
                    .cartItems(new ArrayList<>()) // Initialize with an empty list of items so we don't get a NullPointerException later
                    .build();
            // We immediately save this new empty cart into our database and return it
            return cartRepository.save(newCart); 
        });

        // STEP 3: Find the specific item (food) they want to add to the cart
        // The DTO (Data Transfer Object) from the frontend gives us the ID of the item they clicked on.
        Item item = itemRepository.findById(addItemToCartRequestDto.getItemId())
                // If the item ID doesn't exist in our database, we throw an error
                .orElseThrow(() -> new RuntimeException("Item Not Found to add in the cart"));

        // STEP 4: Check if this exact item is ALREADY inside the user's cart
        // We go through all the items currently in the cart and see if any of them match the item we just found.
        CartItem existingCartItem = cart.getCartItems().stream()
                // IMPORTANT: We compare the ID of the item in the cart with the ID of the new item being added
                .filter((cartItem -> cartItem.getItem().getId().equals(item.getId())))
                .findFirst() // Get the first match if it exists
                .orElse(null); // If no match is found, return null

        // STEP 5: Add the item to the cart or update the quantity
        if (existingCartItem != null) {
            // SCENARIO A: The item is already in the cart!
            // Instead of adding a duplicate row, we just increase the quantity of the existing one.
            long additionalQuantity = Long.valueOf(addItemToCartRequestDto.getQuantity()); // Get how many more they want to add
            existingCartItem.setQuantity(existingCartItem.getQuantity() + additionalQuantity); // Add the new quantity to the old quantity

            // Save the updated existing item back to the database
            cartItemRepository.save(existingCartItem);
        } else {
            // SCENARIO B: The item is NOT in the cart yet!
            // We need to create a brand new CartItem object to link the Item, the Cart, and the Quantity.
            CartItem cartItem = CartItem.builder()
                    .cart(cart) // Link this item to the user's cart
                    .item(item) // Link this to the actual food item
                    .quantity(Long.valueOf(addItemToCartRequestDto.getQuantity())) // Set how many they ordered
                    .build();
                    
            // Save this newly created CartItem into our database
            CartItem savedCartItem = cartItemRepository.save(cartItem);
            
            // Finally, we add this saved CartItem to our cart's list of items
            cart.getCartItems().add(savedCartItem);
            
            // Save the parent Cart so the database knows about this newly added item relationship
            cartRepository.save(cart);
        }
    }

}
