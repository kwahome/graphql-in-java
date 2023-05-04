package io.kwahome.graphqlinjava.repository;

import io.kwahome.graphqlinjava.model.Author;
import io.kwahome.graphqlinjava.model.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @NotNull
    @Query("SELECT DISTINCT book FROM Book book JOIN FETCH book.author authors")
    List<Book> findAll();

    Iterable<Book> findBooksByAuthor(Author author);
}
