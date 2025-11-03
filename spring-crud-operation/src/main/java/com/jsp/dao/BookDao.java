package com.jsp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.jsp.entity.Book;
import com.jsp.repository.BookRepository;

@Repository
public class BookDao {

	@Autowired
	private BookRepository bookRepository;

	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Book> saveAllBook(List<Book> books) {
		return bookRepository.saveAll(books);
	}

	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	public Optional<Book> getBookById(Integer id) {
		return bookRepository.findById(id);
	}

	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}

	public List<Book> findByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}

	public List<Book> findByAuthorAndGenre(String author, String genre) {
		return bookRepository.findByAuthorAndGenre(author, genre);
	}

	public List<Book> findByPriceGreaterThan(Double price) {
		return bookRepository.findByPriceGreaterThan(price);
	}

	public List<Book> findByPriceLessThan(Double price) {
		return bookRepository.findByPriceGreaterThan(price);
	}

	public List<Book> findByPriceBetween(Double stratprice, Double endprice) {
		return bookRepository.findByPriceBetween(stratprice, endprice);
	}

	public List<Book> getBooksByPublishedYear() {
		return bookRepository.getBooksByPublishedYear();
	}

	public List<Book> getBooksByAvailability(Boolean availability) {
		return bookRepository.getBooksByAvailability(availability);
	}

	public List<Book> getBooksByGenre(String genre) {
		return bookRepository.getBooksByGenre(genre);
	}

	public Page<Book> getBookByPaging(int pageNumber, int pageSize) {
		return bookRepository.findAll(PageRequest.of(pageNumber, pageSize));

	}

	public List<Book> getBookBySort(String field) {
		return bookRepository.findAll(Sort.by(field).ascending());
	}

	public Page<Book> getBookByPageAndSort(int pagenumber, int pagesize, String field) {
		return bookRepository.findAll(PageRequest.of(pagenumber, pagesize, Sort.by(field).descending()));
	}
}
