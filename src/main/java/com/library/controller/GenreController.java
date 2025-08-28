package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Genre;
import com.library.service.BookService;

@RestController
@RequestMapping("/api/v1/genre")
@CrossOrigin(origins = "http://localhost:3000")
public class GenreController {
	@Autowired
	BookService bookService;
	
	@GetMapping("/all") //get all genres
	public List<Genre> getAllGenres(){
		return bookService.getAllGenres();
	}
	
	@GetMapping("/allOrdered") //get all genres ordered alphabetically by last name
	public List<Genre> getAllGenresOrdered(){
		return bookService.getAllGenresOrdered();
	}
		
}
