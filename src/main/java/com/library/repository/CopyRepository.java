package com.library.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;
import com.library.entity.Copy;

import jakarta.transaction.Transactional;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Long> {
	//Create: method .save() by default, no need to define it
	
	//Read
	Optional<Copy> findById(Long idCopy);
	List<Copy> findByBook(Book book);
	List<Copy> findByReturnByDate(Date date);
	List<Copy> findByReturnByDateGreaterThan(Date date);
	List<Copy> findByReturnByDateLessThan(Date date);
	List<Copy> findByReturnByDateIsNull();
	List<Copy> findByReturnByDateIsNotNull();
	
	//Update
	@Transactional
	@Modifying
	@Query("UPDATE Copy c set c.returnByDate = ?1 where c.idCopy = ?2")
	void updateReturnByDateById(Date returnByDate, Long idCopy); 
	
	@Transactional
	@Modifying
	@Query("UPDATE Copy c SET c.book = ?1 WHERE c.idCopy = ?2")
	void updateBookForCopy(Book book, Long idCopy);
	
	//Delete: deleteById() defined by default
}
