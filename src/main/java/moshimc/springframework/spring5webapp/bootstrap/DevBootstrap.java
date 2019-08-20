package moshimc.springframework.spring5webapp.bootstrap;

import moshimc.springframework.spring5webapp.model.Author;
import moshimc.springframework.spring5webapp.model.Book;
import moshimc.springframework.spring5webapp.model.Publisher;
import moshimc.springframework.spring5webapp.repositories.AuthorRepository;
import moshimc.springframework.spring5webapp.repositories.BookRepository;
import moshimc.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initializeData() {

        // Publishers
        Publisher underPressure = new Publisher("Under Pressure", "123 Lane Ln");
        Publisher workWork = new Publisher("Work Work", "321 Adams Road");
        publisherRepository.save(underPressure);
        publisherRepository.save(workWork);

        // Randy
        Author randy = new Author("Randy", "Randles");
        Book rrr = new Book("Randy's Really Right", "1234", underPressure);
        randy.getBooks().add(rrr);
        rrr.getAuthors().add(randy);

        authorRepository.save(randy);
        bookRepository.save(rrr);

        // Johnson
        Author johnson = new Author("Johnson", "James");
        Book jsoj = new Book("Johnson Son Of John", "1234", workWork);
        johnson.getBooks().add(jsoj);
        jsoj.getAuthors().add(johnson);

        authorRepository.save(johnson);
        bookRepository.save(jsoj);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initializeData();
    }
}
