package moshimc.springframework.spring5webapp.repositories;

import moshimc.springframework.spring5webapp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
