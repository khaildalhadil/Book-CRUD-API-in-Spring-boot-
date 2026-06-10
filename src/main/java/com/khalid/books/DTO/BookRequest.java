package com.khalid.books.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookRequest {

    @NotNull(message = "title required ...")
    @Size(min = 1, max = 30, message = "Title Should between 1 to 30 char")
    private String title;

    @Size(min = 1, max = 30, message = "author Should between 1 to 30 char")
    private String author;

    @Size(min = 1, max = 30, message = "category Should between 1 to 30 char")
    private String category;

    @Min(value = 1, message = "Rating must be between 1 to 5")
    @Max(value = 5, message = "Rating must be between 1 to 5")
    private int rating;

    public BookRequest(String title, String author, String category, int rating) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
