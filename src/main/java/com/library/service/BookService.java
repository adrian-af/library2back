package com.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.Copy;
import com.library.entity.Genre;

public interface BookService {
	List<Book> getAllBooks();
	List<Author> getAllAuthorsOrdered();
	ResponseEntity<?> insertBook(Book book);
	ResponseEntity<?> updateBook(Book book);
	ResponseEntity<?> updateBookTitleById(String title, Long idBook);
	ResponseEntity<?> deleteBook(Long idBook);
	
	List<Author> getAllAuthors();
	ResponseEntity<?> insertAuthor(Author author);
	ResponseEntity<?> updateAuthor(Author author);
	ResponseEntity<?> updateFirstName(String firstName, Long idAuthor);
	ResponseEntity<?> updateLastName(String lastName, Long idAuthor);
	ResponseEntity<?> deleteAuthor(Long idAuthor);
	
	List<Copy> getAllCopies();
	List<Copy> getAllCopiesOfABook(Long idBook);
	ResponseEntity<?> insertCopy(Copy copy);
	ResponseEntity<?> updateCopy(Copy copy);
	ResponseEntity<?> updateReturnByDate(Date returnByDate, Long idCopy);
	ResponseEntity<?> deleteCopy(Long idCopy);
	
	List<Genre> getAllGenres();
	List<Genre> getAllGenresOrdered();
	ResponseEntity<?> insertGenre(Genre genre);
	ResponseEntity<?> updateGenre(Genre genre);
	ResponseEntity<?> updateGenreName(String genreName, Long idGenre);
	ResponseEntity<?> deleteGenre(Long idCopy);
	
}
