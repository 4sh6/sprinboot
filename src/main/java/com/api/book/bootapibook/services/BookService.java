package com.api.book.bootapibook.services;

import com.api.book.bootapibook.dao.BookRepository;
import com.api.book.bootapibook.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

//    private static List<Book> list = new ArrayList<>();
//
//    static {
//        list.add(new Book(12, "Java complete reference", "java"));
//        list.add(new Book(13, "spring complee reference", "spring"));
//        list.add(new Book(14, "api complere reference", "api"));
//
//    }


    public List<Book> getAllBooks() {
        return (List<Book>) this.bookRepository.findAll();
    }

    public Book getBookById(int id) {
        Book book = null;

        try {
        //            return list.stream().filter(e->e.getId()==id).findFirst().orElse(null);
           book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;

    }

    public Book addBook(Book b){
        Book result = bookRepository.save(b);
        return result;
    }

    public void deleteBook(int bookId) {
        // Reassign the list after filtering out the book with the specified ID
//        list = list.stream()
//                .filter(book -> book.getId() != bookId)  // Keep books whose IDs do not match the given ID
//                .collect(Collectors.toList());
        bookRepository.deleteById(bookId);


    }

    public void updateBook(Book book, int bookId){
//        list = list.stream().map(b->{
//            if(b.getId()==bookId){
//                b.setTitle(book.getTitle());
//                b.setAuthor((book.getAuthor()));
//            }
//            return b;
//        }).collect((Collectors.toList()));
        book.setId(bookId);
        bookRepository.save(book);

    }
}




