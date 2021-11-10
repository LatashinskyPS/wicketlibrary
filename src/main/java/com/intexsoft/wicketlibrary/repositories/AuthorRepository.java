package com.intexsoft.wicketlibrary.repositories;

import com.intexsoft.wicketlibrary.HibernateSessionFactory;
import com.intexsoft.wicketlibrary.entities.Author;
import com.intexsoft.wicketlibrary.entities.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorRepository {
    private static final AuthorRepository authorRepository = new AuthorRepository();

    private AuthorRepository() {
    }

    public static AuthorRepository getInstance() {
        return authorRepository;
    }

    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

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
}
