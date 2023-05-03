package io.kwahome.graphqlinjava.repository;

import io.kwahome.graphqlinjava.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
