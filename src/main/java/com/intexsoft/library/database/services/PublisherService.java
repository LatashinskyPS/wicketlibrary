package com.intexsoft.library.database.services;

import com.intexsoft.library.database.entities.Publisher;
import com.intexsoft.library.database.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Service
public class PublisherService implements Serializable {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public List<Publisher> getAll() {
        return publisherRepository.getAll();
    }

    public Publisher findById(UUID uuid) {
        return publisherRepository.findById(uuid);
    }

    public void delete(Publisher publisher) {
        publisherRepository.delete(publisher);
    }
}
