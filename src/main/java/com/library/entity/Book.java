package com.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBook;
	private String title;
	@ManyToMany
	@JoinTable(
			name="book_author",
			joinColumns = @JoinColumn(name="book_id"), //FK in the table referencing book
			inverseJoinColumns = @JoinColumn(name="author_id")) //FK in the table referencing author
	private List<Author> author;
	@ManyToOne
	@JoinColumn(name="genre_id")
	private Genre genre;
	private boolean fiction;
	private String publisher;
	private int pages;
	private int year;
	private String ISBN;
	@Enumerated(EnumType.STRING)
	private Languages language;
	@OneToMany(mappedBy="book", cascade= CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore //using this to avoid circular references in the response. the cleaner method would be to create a DTO without this attribute and have the controller return the DTO instead of the book object
	private List<Copy> copies;
	private String coverImage; //path to where the image is stored, locally or url
}
