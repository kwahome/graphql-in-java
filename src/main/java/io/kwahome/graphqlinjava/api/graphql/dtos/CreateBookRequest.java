package io.kwahome.graphqlinjava.api.graphql.dtos;

public record CreateBookRequest(String title, String description, Long authorId) {
}
