package io.kwahome.graphqlinjava.api.graphql;

import io.kwahome.graphqlinjava.api.graphql.dtos.CreateBookRequest;
import io.kwahome.graphqlinjava.api.graphql.exceptions.NotFoundException;
import io.kwahome.graphqlinjava.model.Author;
import io.kwahome.graphqlinjava.model.Book;
import io.kwahome.graphqlinjava.repository.AuthorRepository;
import io.kwahome.graphqlinjava.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class BookController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @QueryMapping(name = "books")
    public Iterable<Book> getBooks() {
        return this.bookRepository.findAll();
    }

    @QueryMapping(name = "bookById")
    public Optional<Book> getBookById(@Argument Long bookId) {
        return this.bookRepository.findById(bookId);
    }

    @QueryMapping(name = "booksByAuthor")
    public Iterable<Book> getBooksByAuthor(@Argument Long authorId) throws NotFoundException {
        Author author = this.authorRepository
            .findById(authorId)
            .orElseThrow(() -> new NotFoundException(String.format("Author with id=%s not found", authorId)));

        return this.bookRepository.findBooksByAuthor(author);
    }

    @MutationMapping()
    public Book createBook(@Argument CreateBookRequest createBookRequest) throws NotFoundException {
        if (createBookRequest != null) {
            Long authorId = createBookRequest.authorId();
            Author author = this.authorRepository
                .findById(authorId)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id=%s not found", authorId)));

            Book book = new Book(createBookRequest.title(), createBookRequest.description(), author);

            return this.bookRepository.save(book);
        }

        // else handle this as an error
        return null;
    }
}
