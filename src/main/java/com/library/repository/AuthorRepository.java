package com.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.entity.Author;

import jakarta.transaction.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
	//Create: method .save() by default, no need to define it
	
	//Read
	Optional<Author> findById(Long idAuthor);
	//methods for strict search of last name or first name
	List<Author> findByLastNameIgnoreCase(String authorLastName);
	List<Author> findByFirstNameIgnoreCase(String authorFirstName);
	//methods for search containing the searched term 
	List<Author> findByLastNameContainingIgnoreCase(String authorLastName);
	List<Author> findByFirstNameContainingIgnoreCase(String authorFirstName);
	
	//Update
	@Transactional
	@Modifying
	@Query("UPDATE Author a set a.firstName = ?1 where a.idAuthor = ?2")
	void updateFirstNameById(String firstName, Long idAuthor); 
	
	@Transactional
	@Modifying
	@Query("UPDATE Author a set a.lastName = ?1 where a.idAuthor = ?2")
	void updateLastNameById(String lastName, Long idAuthor); 
	 
	//Delete: deleteById() defined by default
}

