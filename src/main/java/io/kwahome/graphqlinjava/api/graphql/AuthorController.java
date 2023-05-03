package io.kwahome.graphqlinjava.api.graphql;

import io.kwahome.graphqlinjava.api.graphql.dtos.CreateAuthorRequest;
import io.kwahome.graphqlinjava.model.Author;
import io.kwahome.graphqlinjava.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @QueryMapping(name = "authors")
    public Iterable<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

    @QueryMapping(name = "authorById")
    public Optional<Author> getAuthorById(@Argument Long authorId) {
        return this.authorRepository.findById(authorId);
    }

    @MutationMapping()
    public Author createAuthor(@Argument CreateAuthorRequest createAuthorRequest) {
        if (createAuthorRequest != null) {
            Author author = new Author(createAuthorRequest.name(), createAuthorRequest.age());
            return this.authorRepository.save(author);
        }
        // else handle this as an error
        return null;
    }
}
