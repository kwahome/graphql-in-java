package io.kwahome.graphqlinjava.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import io.kwahome.graphqlinjava.model.Book;
import io.kwahome.graphqlinjava.model.Author;
import io.kwahome.graphqlinjava.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    private AuthorRepository authorRepository;

    @Autowired
    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return this.authorRepository.findById(book.getAuthor().getId()).orElseThrow(null);
    }
}
