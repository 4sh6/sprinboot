package com.api.book.bootapibook.dao;

import com.api.book.bootapibook.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    public Book findById(int id);
}
