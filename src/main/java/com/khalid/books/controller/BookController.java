package com.khalid.books.controller;

import com.khalid.books.modle.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/api/books")
public class BookController {


    private final List<Book> books = new ArrayList<Book>();

    public BookController() {
        Book b = new Book();
        b.setId(1);
        b.setAuthor("Khalid");
        b.setName("Hello");
        books.add(b);
    }

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/api/book")
    public Book getBookByAuthorAndName(@RequestParam String name, String author) {
        return books.stream()
                .filter(b -> b.getName().equalsIgnoreCase(name) && b.getAuthor().equalsIgnoreCase(author))
                .findFirst()
                .orElse(null);
    }


    @GetMapping("/api/book/{id}")
    public Book getBookById(@PathVariable int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }


    @PostMapping("/api/book")
    public Book AddBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @PutMapping("/api/book/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updateBook) {
        for(int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                books.set(i, updateBook);
                return updateBook;
            }
        }
    return updateBook;
    }

    @DeleteMapping("api/book/{id}")
    public void deleteBook(@PathVariable int id) {

        books.removeIf(book -> book.getId() == id);

//        Book b = books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
//        for (int i = 0; i < books.size(); i++) {
//            if (books.get(i).getId() == id) {
//                books.remove(i);
//                return;
//            }
//        }


    }

}

