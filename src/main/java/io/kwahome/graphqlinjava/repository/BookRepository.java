package io.kwahome.graphqlinjava.repository;

import io.kwahome.graphqlinjava.model.Author;
import io.kwahome.graphqlinjava.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Iterable<Book> findBooksByAuthor(Author author);
}
