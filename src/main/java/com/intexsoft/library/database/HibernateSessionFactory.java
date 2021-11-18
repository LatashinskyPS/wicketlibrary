package com.intexsoft.library.database;

import com.intexsoft.library.database.entities.Author;
import com.intexsoft.library.database.entities.Book;
import com.intexsoft.library.database.entities.Publisher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateSessionFactory {
    @Bean
    public SessionFactory sessionFactory() {
        return new Configuration()
                .addAnnotatedClass(Author.class).addAnnotatedClass(Publisher.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
    }
}
