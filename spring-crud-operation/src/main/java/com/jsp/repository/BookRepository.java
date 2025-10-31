package com.jsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByAuthor(String author);

	List<Book> findByAuthorAndGenre(String author, String genre);

	List<Book> findByPriceGreaterThan(Double price);

	List<Book> findByPriceLessThan(Double price);

	List<Book> findByPriceBetween(Double stratprice, Double endprice);

	@Query("select b from Book b where b.publishedYear=1960")
	List<Book> getBooksByPublishedYear();

	@Query("select b from Book b where b.availability=?1")
	List<Book> getBooksByAvailability(Boolean availability);

	@Query("select b from Book b where b.genre = :genre")
	List<Book> getBooksByGenre(String genre);

}
