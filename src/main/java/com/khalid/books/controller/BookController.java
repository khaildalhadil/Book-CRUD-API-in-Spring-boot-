package com.khalid.books.controller;

import com.khalid.books.DTO.BookRequest;
import com.khalid.books.modle.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {


    private final List<Book> books = new ArrayList<Book>();

    public BookController() {
        initAllBooks();
    }

    public void initAllBooks() {
        books.add(new Book(1, "Java Basics", "John Smith", "Programming", 4));
        books.add(new Book(2, "Java Basics", "Michael Brown", "Programming", 5));
        books.add(new Book(3, "Java Advanced", "David Wilson", "Programming", 5));
        books.add(new Book(4, "Spring Boot Guide", "Ahmed Ali", "Programming", 4));
        books.add(new Book(5, "Spring Boot Guide", "Sara Khan", "Programming", 5));
        books.add(new Book(6, "Money Mindset", "James Clear", "Finance", 4));
        books.add(new Book(7, "Money Mindset", "Morgan Housel", "Finance", 5));
        books.add(new Book(8, "Invest Smart", "Robert Kiyosaki", "Finance", 4));
        books.add(new Book(9, "Healthy Living", "Mark Lee", "Health", 4));
        books.add(new Book(10, "Healthy Living", "Anna White", "Health", 5));
    }

    @GetMapping
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Book AddBook(@RequestBody BookRequest bookRequest) {

        int id = books.isEmpty() ? 1: books.get(books.size() - 1).getId() + 1;

        Book book = convertBook(id, bookRequest);

        books.add(book);
        return book;
    }


    @PutMapping("/{id}")
    public void updateBook(@PathVariable int id, @RequestBody BookRequest bookReq) {
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                Book bookToUpdate = convertBook(id, bookReq);
                books.set(i, bookToUpdate);
                return ;
            }
        }

    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable int id) {
        books.removeIf(book -> book.getId() == id);

    }

    private Book convertBook(int id, BookRequest bookReq) {
        return new Book(id, bookReq.getTitle(), bookReq.getAuthor(), bookReq.getCategory(), bookReq.getRating());
    }


}

