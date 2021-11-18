package com.intexsoft.library.database.services;

import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Service
public class BookService implements Serializable{

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Book findById(UUID uuid) {
        return bookRepository.findById(uuid);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
