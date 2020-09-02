package com.williamheng.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.coxautodev.graphql.tools.GraphQLResolver
import com.williamheng.graphql.dto.*
import java.util.*

class QueryResolver(val data: Collection<Member>) : GraphQLQueryResolver {

    fun members(): Collection<Member> {
        return data
    }

}

private val books = mutableListOf(
    Book("book1", "Book One", "author1"),
    Book("book2", "Book Two", "author2"),
    Book("book3", "Book Three", "author3")
)


private val movies = mutableListOf(
    Movie("movie1", "Titanic", "author1"),
    Movie("movie2", "Sunset On The Dew", "author3")
)

class BookQueryResolver : GraphQLQueryResolver {

    fun books(): Collection<Book> {
        return books
    }

    suspend fun book(bookId: String): Book? {
        return books.find { it.id == bookId }
    }

    fun artifacts(): List<Artifact> {
        return books + movies
    }

}

private val authors = mutableMapOf(
    "author1" to "Winston Edgar",
    "author2" to "Muruthi Parkinson",
    "author3" to "Ng Siew Mei"
)

class BookResolver : GraphQLResolver<Book> {
    suspend fun author(book: Book): Author {
        val authorId = book.authorId
        return Author(authorId, authors[authorId]!!)
    }
}

class MovieResolver: GraphQLResolver<Movie> {
    fun author(movie: Movie): Author {
        val authorId = movie.authorId
        return Author(authorId, authors[authorId]!!)
    }
}

class BookMutationResolver: GraphQLMutationResolver {
    fun createBook(name: String, authorName: String): Book {
        val authorId = UUID.randomUUID().toString()
        val book = Book(UUID.randomUUID().toString(), name, authorId)
        books.add(book)
        authors[authorId] = authorName
        return book
    }
}
