package com.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.library.entity.Book;

import jakarta.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	//Create: method .save() by default, no need to define it
	
	//Read
	Optional<Book> findById(Long idBook);
	List<Book> findAll();
	List<Book> findByTitleIgnoreCase(String title);
	List<Book> findByTitleContainingIgnoreCase(String title);
	List<Book> findByAuthorLastName(String authorLastName);
	List<Book> findByYear(int year);
	List<Book> findByPagesGreaterThan(int pages);
	List<Book> findByPagesLessThan(int pages);
	//Update
	@Transactional
	@Modifying
	@Query("UPDATE Book b set b.title = ?1 where b.idBook = ?2")
	void updateTitleById(String title, Long idBook); 
	
	//Delete deleteById() is defined by default
	}
