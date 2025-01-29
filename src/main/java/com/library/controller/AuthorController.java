package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Author;
import com.library.service.BookService;

@RestController
@RequestMapping("/api/v1/author")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorController {
	@Autowired
	BookService bookService;
	
	@GetMapping("/all") //get all authors
	public List<Author> getAllAuthors(){
		return bookService.getAllAuthors();
	}
	
	@PostMapping("/insertAuthor") //insert new author
	public ResponseEntity<?> createAuthor(@RequestBody Author author){
		return bookService.insertAuthor(author);
	}
}
