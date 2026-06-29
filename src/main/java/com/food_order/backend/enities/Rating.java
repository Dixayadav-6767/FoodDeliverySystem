package com.food_order.backend.enities;

import jakarta.persistence.*;

@Entity
@Table(name = "Rating_Table")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long RatingId;
    Long ItemId;
    Long User_id;
    String Reviews;
    Long Rating;

    public Rating(){

    }

    public Rating(Long ratingId, Long itemId, Long user_id, String reviews, Long rating) {
        RatingId = ratingId;
        ItemId = itemId;
        User_id = user_id;
        Reviews = reviews;
        Rating = rating;
    }

    public Long getRatingId() {
        return RatingId;
    }

    public void setRatingId(Long ratingId) {
        RatingId = ratingId;
    }

    public Long getItemId() {
        return ItemId;
    }

    public void setItemId(Long itemId) {
        ItemId = itemId;
    }

    public Long getUser_id() {
        return User_id;
    }

    public void setUser_id(Long user_id) {
        User_id = user_id;
    }

    public String getReviews() {
        return Reviews;
    }

    public void setReviews(String reviews) {
        Reviews = reviews;
    }

    public Long getRating() {
        return Rating;
    }

    public void setRating(Long rating) {
        Rating = rating;
    }
}
