package com.intexsoft.library.database.services;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.repositories.AuthorRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorService implements Serializable {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    public List<Author> getByBook(Book book) {
        return authorRepository.getByBook(book);
    }

    public Author findById(UUID uuid) {
        return authorRepository.findById(uuid);
    }

    public void delete(Author author) {
        authorRepository.delete(author);
    }
}
