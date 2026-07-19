package com.food_order.backend.services.implementation;

import com.food_order.backend.Repositories.*;
import com.food_order.backend.dto.PlaceOrderRequestDto;
import com.food_order.backend.enities.*;
import com.food_order.backend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;

    @Override
    public void placeOrder (PlaceOrderRequestDto placeOrderRequestDto){

        // STEP 1: Identify who is making the request
        // Get the authentication details of the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Extract the user's email

        // Fetch the full user details from the database using their email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // STEP 2: Fetch the cart that contains the items they want to buy
        // We get the cartId directly from the request sent by the frontend
        Cart cart = cartRepository.getById(placeOrderRequestDto.getCartId());

        // Validate that the cart actually exists
        if(cart == null){
            throw new RuntimeException("Cart Not found");
        }

        // STEP 3: Handle the Delivery Address
        // The user can either send us an ID of an address they saved previously, OR a completely new address to save.
        Address deliveryAddress = null;
        
        if (placeOrderRequestDto.getAddressId() != null) {
            // SCENARIO 1: The user selected a previously saved address (they sent us an addressId)
            // We fetch this existing address from the database
            deliveryAddress = addressRepository.findById(placeOrderRequestDto.getAddressId())
                    .orElseThrow(() -> new RuntimeException("Address not found"));

        }  else if (placeOrderRequestDto.getAddressDto() != null) {
            // SCENARIO 2: The user typed in a brand new address (they sent us an addressDto object)
            // We create a new Address object to save it in our database for future use
            Address newAddress = new Address();

            // We copy all the fields from the incoming request (DTO) to our database entity
            newAddress.setState(placeOrderRequestDto.getAddressDto().getState());
            newAddress.setCity(placeOrderRequestDto.getAddressDto().getCity());
            newAddress.setCountry(placeOrderRequestDto.getAddressDto().getCountry());
            newAddress.setUser_address(placeOrderRequestDto.getAddressDto().getUser_address());
            newAddress.setPincode(placeOrderRequestDto.getAddressDto().getPincode());

            // Link this brand new address permanently to our logged-in user
            newAddress.setUser(user); 

            // Save the new address to the database and assign it to our deliveryAddress variable
            deliveryAddress = addressRepository.save(newAddress);
        } else {
            // SCENARIO 3: The user sent neither an old address ID nor a new address object
            // We cannot place an order without knowing where to deliver it!
            throw new RuntimeException("Delivery address is required to place an order");
        }

        // STEP 4: Create the actual Order
        // We build a new Order object, link it to the user, set it to PLACED, and give it an empty list of items for now
        Order order = Order.builder()
                .user(user)
                .orderItems(new ArrayList<>())
                .orderStatus(OrderStatus.PLACED)
                .build();

        // STEP 5: Convert CartItems into OrderItems
        // A 'CartItem' represents something sitting in a cart. An 'OrderItem' represents something permanently bought in an order.
        // We loop (stream) through every item currently inside the user's cart
        List<OrderItem> orderItems = cart.getCartItems().stream().map((cartItem)->{
            // For every CartItem, we build a corresponding OrderItem
            return OrderItem.builder()
                    .order(order) // Link the item to the order we just created above
                    .item(cartItem.getItem()) // Copy the food item reference
                    .quantity(cartItem.getQuantity()) // Copy the quantity they wanted
                    .price_at_item(null) // NOTE: You should ideally save the current price here so if prices change later, past orders don't break
                    .build();
        }).toList();

        // Attach our newly converted list of OrderItems to the main Order
        order.setOrderItems(orderItems);
        
        // Save the completed Order (and all its items) into the database!
        orderRepository.save(order);

        // STEP 6: Clear the Cart
        // Now that the order is successfully placed, we must empty the user's cart so they can start fresh
        
        // First, delete all the physical CartItem rows from the database permanently
        cartItemRepository.deleteAll(cart.getCartItems());
        
        // Second, clear the list in our current Cart object
        cart.getCartItems().clear();
        
        // Finally, save the empty cart back to the database
        cartRepository.save(cart);
    }
}
