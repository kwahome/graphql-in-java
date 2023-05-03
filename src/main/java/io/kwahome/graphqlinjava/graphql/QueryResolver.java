package io.kwahome.graphqlinjava.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import io.kwahome.graphqlinjava.model.Book;
import io.kwahome.graphqlinjava.model.Author;
import io.kwahome.graphqlinjava.repository.AuthorRepository;
import io.kwahome.graphqlinjava.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public QueryResolver(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

    public Iterable<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    public long getAuthorsCount() {
        return this.authorRepository.count();
    }

    public long getBooksCount() {
        return this.bookRepository.count();
    }
}
