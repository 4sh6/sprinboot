package com.api.book.bootapibook.controller;


import com.api.book.bootapibook.entities.Book;
import com.api.book.bootapibook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getallBooks(){
        List<Book> list = this.bookService.getAllBooks();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getbook(@PathVariable("id") int id){
        Book book = bookService.getBookById(id);
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));

    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try {
            Book b =this.bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/books/{bookID}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookID") int bookId){
        try {
            this.bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PutMapping("/books/{bookID}")
//    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookID") int bookId){
//        try {
//            this.bookService.updateBook(book, bookId);
//            return ResponseEntity.ok(book);
//        }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PutMapping("/books/{bookID}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookID") int bookId) {
        try {
            // Check if the book exists before updating
            Book existingBook = bookService.getBookById(bookId);
            if (existingBook == null) {
                // If the book doesn't exist, return 404 Not Found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // If the book exists, perform the update
            bookService.updateBook(book, bookId);

            // Return 200 OK with the updated book
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            // In case of any exception, return 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
