package com.khalid.books.controller;

import com.khalid.books.DTO.BookRequest;
import com.khalid.books.modle.Book;
import com.khalid.books.utils.BookUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Book> getBooks() {
        return books;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable @Min(1) int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Book AddBook(@Valid @RequestBody BookRequest bookRequest) {

        int id = books.isEmpty() ? 1: books.get(books.size() - 1).getId() + 1;

        Book book = BookUtils.convertBook(id, bookRequest);

        books.add(book);
        return book;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateBook(@PathVariable @Min(1) int id, @Valid @RequestBody BookRequest bookReq) {
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, BookUtils.convertBook(id, bookReq));
                return ;
            }
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable @Min(1) int id) {
        books.removeIf(book -> book.getId() == id);

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/*")
    public String notFound() {
        return "Not FOund 404";
    }





}

