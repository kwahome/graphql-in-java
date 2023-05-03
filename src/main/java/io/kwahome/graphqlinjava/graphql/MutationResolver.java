package io.kwahome.graphqlinjava.graphql;

import io.kwahome.graphqlinjava.exceptions.NotFoundException;
import io.kwahome.graphqlinjava.model.Book;
import io.kwahome.graphqlinjava.model.Author;
import io.kwahome.graphqlinjava.repository.BookRepository;
import io.kwahome.graphqlinjava.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MutationResolver {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public MutationResolver(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author createAuthor(String name, Integer age) {
        Author author = new Author(name, age);

        this.authorRepository.save(author);

        return author;
    }

    public Book createBook(String title, String description, Long authorId) {
        Book book = new Book(title, description, new Author(authorId));

        this.bookRepository.save(book);

        return book;
    }

    public boolean deleteBook(Long id) {
        this.bookRepository.deleteById(id);
        return true;
    }

    public Book updateBook(Long id, String title, String description) throws NotFoundException {
        Optional<Book> optionalBook = this.bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book tutorial = optionalBook.get();

            if (title != null) {
                tutorial.setTitle(title);
            }

            if (description != null) {
                tutorial.setDescription(description);
            }

            this.bookRepository.save(tutorial);

            return tutorial;
        }

        throw new NotFoundException(String.format("Book with id=%s was not found", id));
    }
}
