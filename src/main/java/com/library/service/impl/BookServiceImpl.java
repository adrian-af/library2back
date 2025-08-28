package com.library.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.Copy;
import com.library.entity.Genre;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.CopyRepository;
import com.library.repository.GenreRepository;
import com.library.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private CopyRepository copyRepository;
	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public ResponseEntity<?> insertBook(Book book) {
		try {
			Book savedBook = bookRepository.save(book); //returns the book if the save is successful
			if(checkInsertBook(savedBook)) {
				return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred when saving the book to the database. Please try again later or contact support: "+ book.toString());
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when saving the book to the database. Please try again later or contact support: " + book.toString());
		}
	}

	public boolean checkInsertBook(Book book) {
		if(book == null) {
			//book not inserted successfully
			return false;
		}
		else if(book.getIdBook() != null ){
			//book is valid, so it was inserted successfully
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public ResponseEntity<?> insertAuthor(Author author) {
		try {
			Author savedAuthor = authorRepository.save(author); //returns the author if the save is successful
			if(checkInsertAuthor(savedAuthor)) {
				return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred when saving the author to the database. Please try again later or contact support: "+ author.toString());
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when saving the author to the database. Please try again later or contact support: " + author.toString() + e.getMessage());
		}
	}
	
	public boolean checkInsertAuthor(Author author) {
		if(author == null) {
			//author not inserted successfully
			return false;
		}
		else if(author.getIdAuthor() != null ){
			//author is valid, so it was inserted successfully
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public ResponseEntity<?> updateBook(Book book) {
		try {
			Optional<Book> optionalCurrentBook = bookRepository.findById(book.getIdBook());
			if(optionalCurrentBook.isPresent()) {
				Book currentBook = optionalCurrentBook.get();
				if(book.equals(currentBook)) {
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Not saved because no data was changed for the book");
				}
				Book updatedBook = bookRepository.save(book);
				if(checkInsertBook(updatedBook)) {
					return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
				}
				else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred when updating the book. Please try again later or contact support: " + book.toString());
				}
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no book with that ID in the DB: " + book.toString());
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when updating the book. Please try again later or contact support: " + book.toString() + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateBookTitleById(String title, Long idBook) {	
		try {
			Optional<Book> optionalBook = bookRepository.findById(idBook);
			if(optionalBook.isPresent()) {
				Book book = optionalBook.get();
				bookRepository.updateTitleById(title, idBook);
				return ResponseEntity.status(HttpStatus.OK).body(book);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no book with that ID in the DB: " + idBook);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when updating the book title. Please try again later or contact support: " + title + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteBook(Long idBook) {
		try {
			Optional<Book> optionalBook = bookRepository.findById(idBook);
			if(optionalBook.isPresent()) {
				Book book = optionalBook.get();
				bookRepository.delete(book);
				return ResponseEntity.status(HttpStatus.OK).body(book);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no book with that ID in the DB: " + idBook);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when deleting the book. Please try again later or contact support: " + idBook + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateAuthor(Author author) {
		try {
			Optional<Author> optionalCurrentAuthor = authorRepository.findById(author.getIdAuthor());
			if(optionalCurrentAuthor.isPresent()) {
				Author currentAuthor = optionalCurrentAuthor.get();
				if(author.equals(currentAuthor)) {
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Not saved because no data was changed for the author");
				}
				Author updatedAuthor = authorRepository.save(author);
				if(checkInsertAuthor(updatedAuthor)) {
					return ResponseEntity.status(HttpStatus.OK).body(updatedAuthor);
				}
				else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred when updating the author. Please try again later or contact support: " + author.toString());
				}
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no author with that ID in the DB: " + author.toString());
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when updating the author. Please try again later or contact support: " + author.toString() + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateFirstName(String firstName, Long idAuthor) {
		try {
			Optional<Author> optionalAuthor= authorRepository.findById(idAuthor);
			if(optionalAuthor.isPresent()) {
				Author author = optionalAuthor.get();
				authorRepository.updateFirstNameById(firstName, idAuthor);
				return ResponseEntity.status(HttpStatus.OK).body(author);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no author with that ID in the DB: " + idAuthor);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when updating the author's first name. Please try again later or contact support: " + firstName+ e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateLastName(String lastName, Long idAuthor) {
		try {
			Optional<Author> optionalAuthor= authorRepository.findById(idAuthor);
			if(optionalAuthor.isPresent()) {
				Author author = optionalAuthor.get();
				authorRepository.updateLastNameById(lastName, idAuthor);
				return ResponseEntity.status(HttpStatus.OK).body(author);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no author with that ID in the DB: " + idAuthor);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when updating the author's last name. Please try again later or contact support: " + lastName + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteAuthor(Long idAuthor) {
		try {
			Optional<Author> optionalAuthor = authorRepository.findById(idAuthor);
			if(optionalAuthor.isPresent()) {
				Author author = optionalAuthor.get();
				authorRepository.delete(author);
				return ResponseEntity.status(HttpStatus.OK).body(author);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no author with that ID in the DB: " + idAuthor);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when deleting the author. Please try again later or contact support: " + idAuthor + e.getMessage());
		}
	}

	@Override
	public List<Copy> getAllCopies() {
		return copyRepository.findAll();			
	}

	@Override
	public List<Copy> getAllCopiesOfABook(Long idBook) {
		try {
			Optional<Book> optionalBook = bookRepository.findById(idBook);
			if(optionalBook.isPresent()) {
				Book book = optionalBook.get();
				return copyRepository.findByBook(book);				
			}
			else {
				return new ArrayList<Copy>();
			}
		}catch(Exception e) {
			return new ArrayList<Copy>();
		}
	}

	@Override
	public ResponseEntity<?> insertCopy(Copy copy) {
		try {
			Copy savedCopy = copyRepository.save(copy); //returns the author if the save is successful
			if(checkInsertCopy(savedCopy)) {
				return ResponseEntity.status(HttpStatus.CREATED).body(savedCopy);
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred when saving the copy to the database. Please try again later or contact support: "+ copy.toString());
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when saving the copy to the database. Please try again later or contact support: " + copy.toString() + e.getMessage());
		}
	}
	public boolean checkInsertCopy(Copy copy) {
		if(copy == null) {
			//copy not inserted successfully
			return false;
		}
		else if(copy.getIdCopy() != null ){
			//copy is valid, so it was inserted successfully
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public ResponseEntity<?> updateCopy(Copy copy) {
		try {
			Optional<Copy> optionalCurrentCopy = copyRepository.findById(copy.getIdCopy());
			if(optionalCurrentCopy.isPresent()) {
				Copy currentCopy = optionalCurrentCopy.get();
				if(copy.equals(currentCopy)) {
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Not saved because no data was changed for the copy");
				}
				Copy updatedCopy = copyRepository.save(copy);
				if(checkInsertCopy(updatedCopy)) {
					return ResponseEntity.status(HttpStatus.OK).body(updatedCopy);
				}
				else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred when updating the copy. Please try again later or contact support: " + copy.toString());
				}
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no copy with that ID in the DB: " + copy.toString());
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when updating the copy. Please try again later or contact support: " + copy.toString() + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateReturnByDate(Date returnByDate, Long idCopy) {
		try {
			Optional<Copy> optionalCopy= copyRepository.findById(idCopy);
			if(optionalCopy.isPresent()) {
				Copy copy = optionalCopy.get();
				copyRepository.updateReturnByDateById(returnByDate, idCopy);
				return ResponseEntity.status(HttpStatus.OK).body(copy);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no copy with that ID in the DB: " + idCopy);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when updating the copy return by date. Please try again later or contact support: " + returnByDate.toString() + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteCopy(Long idCopy) {
		try {
			Optional<Copy> optionalCopy = copyRepository.findById(idCopy);
			if(optionalCopy.isPresent()) {
				Copy copy = optionalCopy.get();
				copyRepository.delete(copy);
				return ResponseEntity.status(HttpStatus.OK).body(copy);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no copy with that ID in the DB: " + idCopy);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when deleting the copy. Please try again later or contact support: " + idCopy + e.getMessage());
		}
	}

	@Override
	public List<Genre> getAllGenres() {
		return genreRepository.findAll();
	}

	@Override
	public ResponseEntity<?> insertGenre(Genre genre) {
		try {
			Genre savedGenre = genreRepository.save(genre); //returns the genre if the save is successful
			if(checkInsertGenre(savedGenre)) {
				return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
			}
			else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred when saving the genre to the database. Please try again later or contact support: "+ genre.toString());
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when saving the genre to the database. Please try again later or contact support: " + genre.toString() + e.getMessage());
		}
	}
	public boolean checkInsertGenre(Genre genre) {
		if(genre == null) {
			//genre not inserted successfully
			return false;
		}
		else if(genre.getIdGenre() != null ){
			//genre is valid, so it was inserted successfully
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public ResponseEntity<?> updateGenre(Genre genre) {
		try {
			Optional<Genre> optionalCurrentGenre = genreRepository.findById(genre.getIdGenre());
			if(optionalCurrentGenre.isPresent()) {
				Genre currentGenre = optionalCurrentGenre.get();
				if(genre.equals(currentGenre)) {
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Not saved because no data was changed for the genre");
				}
				Genre updatedGenre = genreRepository.save(genre);
				if(checkInsertGenre(updatedGenre)) {
					return ResponseEntity.status(HttpStatus.OK).body(updatedGenre);
				}
				else {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred when updating the genre. Please try again later or contact support: " + genre.toString());
				}
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no genre with that ID in the DB: " + genre.toString());
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when updating the genre. Please try again later or contact support: " + genre.toString() + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> updateGenreName(String genreName, Long idGenre) {
		try {
			Optional<Genre> optionalGenre= genreRepository.findById(idGenre);
			if(optionalGenre.isPresent()) {
				Genre genre = optionalGenre.get();
				genreRepository.updateNameById(genreName, idGenre);
				return ResponseEntity.status(HttpStatus.OK).body(genre);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no genre with that ID in the DB: " + idGenre);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when updating the genre return by date. Please try again later or contact support: " + idGenre + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteGenre(Long idGenre) {
		try {
			Optional<Genre> optionalGenre = genreRepository.findById(idGenre);
			if(optionalGenre.isPresent()) {
				Genre genre = optionalGenre.get();
				genreRepository.delete(genre);
				return ResponseEntity.status(HttpStatus.OK).body(genre);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no genre with that ID in the DB: " + idGenre);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred when deleting the genre. Please try again later or contact support: " + idGenre + e.getMessage());
		}
	}

	@Override
	public List<Author> getAllAuthorsOrdered() {
		return authorRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public List<Genre> getAllGenresOrdered() {
		return genreRepository.findAllByOrderByNameAsc();
	}
}
