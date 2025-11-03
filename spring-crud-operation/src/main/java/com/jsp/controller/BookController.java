package com.jsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jsp.dto.ResponseStructure;
import com.jsp.entity.Book;
import com.jsp.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	// Create a single book
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}

	// Create multiple books
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Book>>> saveAllBooks(@RequestBody List<Book> books) {
		return bookService.saveAllBook(books);
	}

	// Get all books
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
		return bookService.getAllBook();
	}

	// Get book by ID
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Integer id) {
		return bookService.getBookById(id);

	}

	// Update book
	@PutMapping
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);

	}

	// Delete book by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
		return bookService.deleteBook(id);
	}

	// find by author
	@GetMapping("/author/{author}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthod(@PathVariable String author) {
		return bookService.findByAuthor(author);
	}

	// find by author and genre
	@GetMapping("/author/{author}/genre/{genre}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthodAndGenre(@PathVariable String author,
			@PathVariable String genre) {
		return bookService.findByAuthorAndGenre(author, genre);
	}

	// find By Price GreaterThan
	@GetMapping("/price/greaterthan/{price}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGreaterThanPrice(@PathVariable Double price) {
		return bookService.findByPriceGreaterThan(price);
	}

	// find By Price Less Than
	@GetMapping("/price/lessthan/{price}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByLessThanPrice(@PathVariable Double price) {
		return bookService.findByPriceLessThan(price);
	}

	// find By Price Between
	@GetMapping("/price/between/{stratprice}/{endprice}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPriceBetween(@PathVariable Double stratprice,
			@PathVariable Double endprice) {
		return bookService.findByPriceBetween(stratprice, endprice);
	}

	// using Query
	@GetMapping("/year/1960")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByPublishedYear1960() {
		return bookService.getBooksByPublishedYear();
	}

	// using Query
	@GetMapping("/availability/{availability}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByAvailability(@PathVariable Boolean availability) {
		return bookService.getBooksByAvailability(availability);
	}

	// using Query
	@GetMapping("/genre/{genre}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByGenre(@PathVariable String genre) {
		return bookService.getBookByGenre(genre);
	}

	// pagination
	@GetMapping("/paging/{pageNumber}/{pageSize}")
	public ResponseEntity<ResponseStructure<Page<Book>>> getBooksByPaging(@PathVariable int pageNumber,
			@PathVariable int pageSize) {
		return bookService.getBookByPage(pageNumber, pageSize);
	}

	// sorting
	@GetMapping("/{field}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByPaging(@PathVariable String field) {
		return bookService.getBookBySort(field);
	}
	// paging and sorting
	@GetMapping("/paging/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Book>>> getBooksByPaging(@PathVariable int pageNumber,
			@PathVariable int pageSize, @PathVariable String field) {
		return bookService.getBookByPageAndSort(pageNumber, pageSize, field);
	}

}
