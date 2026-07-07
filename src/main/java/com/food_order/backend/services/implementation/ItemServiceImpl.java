package com.food_order.backend.services.implementation;

import com.cloudinary.Cloudinary;
import com.food_order.backend.Repositories.CategoryRepository;
import com.food_order.backend.Repositories.ItemRepository;
import com.food_order.backend.dto.AddItemRequestDto;
import com.food_order.backend.enities.Category;
import com.food_order.backend.enities.Item;
import com.food_order.backend.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final Cloudinary cloudinary;

    @Override
    public void addItem(AddItemRequestDto addItemRequestDto , MultipartFile image) {

        Category category = categoryRepository.findById(addItemRequestDto.getCategoryId()).orElseThrow(()-> new RuntimeException("Category Not found"));

        String imageUrl = null;

        if(!image.isEmpty()){
            try {
                Map data = this.cloudinary.uploader().upload(image.getBytes() , null);
                imageUrl = (String) data.get("secure_url");
            }catch (Exception ex) {
                throw new RuntimeException("Failed to upload image");
            }
        }


        Item item = Item.builder()
                .name(addItemRequestDto.getItemName())
                .description(addItemRequestDto.getItemDescription())
                .image(imageUrl)
                .category(category)
                .price(addItemRequestDto.getPrice())
                .build();
        itemRepository.save(item);
    }
}
