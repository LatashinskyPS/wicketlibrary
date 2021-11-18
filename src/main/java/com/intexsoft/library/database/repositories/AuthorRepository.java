package com.intexsoft.library.database.repositories;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AuthorRepository implements Serializable {
    private final SessionFactory sessionFactory;

    public AuthorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        if (author.getId() != null) {
            session.update(author);
        } else {
            session.save(author);
        }
        session.getTransaction().commit();
        session.close();
    }

    public List<Author> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Author> publisherList = session.createQuery("from Author ").getResultList();
        session.getTransaction().commit();
        session.close();
        return publisherList;
    }

    public List<Author> getByBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Author> authorList = new ArrayList<>();
        if (book.getId() != null) {
            session.refresh(book);
            authorList = book.getAuthorList()
                    .stream().distinct().collect(Collectors.toList());
        }
        session.getTransaction().commit();
        session.close();
        return authorList;
    }

    public Author findById(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Author author = session.get(Author.class, uuid);
        session.getTransaction().commit();
        session.close();
        return author;
    }

    public void delete(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.refresh(author);
        session.delete(author);
        session.getTransaction().commit();
        session.close();
    }
}
