package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.service.BookService;
import com.library.entity.Book;

@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
	@Autowired
	BookService bookService;
	
	@GetMapping("/all") //get all books
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@PostMapping("/insertBook") //insert new book
	public ResponseEntity<?> createBook(@RequestBody Book book){
		return bookService.insertBook(book);	
	}
	
	@PatchMapping("/modifyBook")
	public ResponseEntity<?> modifyBook(@RequestBody Book book){
		return bookService.updateBook(book);
	}
	
	@PatchMapping("{id}/modifyTitle")
	public ResponseEntity<?> modifyTitle(@PathVariable Long id, @RequestBody String title){
		return bookService.updateBookTitleById(title, id);
	}
	
	@DeleteMapping("{id}/deleteBook")
	public ResponseEntity<?> deleteBook(@PathVariable Long id){
		return bookService.deleteBook(id);
	}
}
