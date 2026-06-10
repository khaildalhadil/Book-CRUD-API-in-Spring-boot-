package com.khalid.books.utils;

import com.khalid.books.DTO.BookRequest;
import com.khalid.books.modle.Book;

public final class BookUtils {

    public static Book convertBook(int id, BookRequest bookReq) {
        return new Book(id, bookReq.getTitle(), bookReq.getAuthor(), bookReq.getCategory(), bookReq.getRating());
    }

}
