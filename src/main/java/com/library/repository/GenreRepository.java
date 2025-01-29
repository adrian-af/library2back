package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.library.entity.Genre;

import jakarta.transaction.Transactional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
		//Create: method .save() by default, no need to define it
	
		//Read
		Optional<Genre> findById(Long idGenre);

		//Update
		@Transactional
		@Modifying
		@Query("UPDATE Genre g SET g.name = ?1 WHERE g.idGenre = ?2")
		void updateNameById(String name, Long idGenre);
		
		//Delete: deleteById() defined by default
}
