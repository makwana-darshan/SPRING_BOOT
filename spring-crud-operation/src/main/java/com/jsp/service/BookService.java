package com.jsp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.dao.BookDao;
import com.jsp.dto.ResponseStructure;
import com.jsp.entity.Book;
import com.jsp.exception.IdNotFoundException;
import com.jsp.exception.NoRecordAvailableException;
import com.jsp.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;

	// Save a single book
	public ResponseEntity<ResponseStructure<Book>> saveBook(Book book) {
		ResponseStructure<Book> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Book saved successfully!");
		response.setData(bookDao.saveBook(book));

		return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.CREATED);
	}

	// Save all books
	public ResponseEntity<ResponseStructure<List<Book>>> saveAllBook(List<Book> books) {
		ResponseStructure<List<Book>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("All books saved successfully!");
		response.setData(bookDao.saveAllBook(books));

		return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.CREATED);
	}

	// Get all books
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBook() {
		List<Book> books = bookDao.getAllBook();
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All books fetched successfully!");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books available!");
		}
	}

	// Get book by ID
	public ResponseEntity<ResponseStructure<Book>> getBookById(Integer id) {
		Optional<Book> optionalBook = bookDao.getBookById(id);
		ResponseStructure<Book> response = new ResponseStructure<>();

		if (optionalBook.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book found!");
			response.setData(optionalBook.get());
			return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Book not found for ID: " + id);
		}
	}

	// Update book
	public ResponseEntity<ResponseStructure<Book>> updateBook(Book book) {
		ResponseStructure<Book> response = new ResponseStructure<>();
		Optional<Book> optional = bookDao.getBookById(book.getId());

		if (optional.isPresent()) {
			Book updatedBook = bookDao.saveBook(book);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book updated successfully!");
			response.setData(updatedBook);
			return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Book not found for ID: " + book.getId());
		}
	}

	// Delete book by ID
	public ResponseEntity<ResponseStructure<String>> deleteBook(Integer id) {
		Optional<Book> opt = bookDao.getBookById(id);
		ResponseStructure<String> response = new ResponseStructure<>();

		if (opt.isPresent()) {
			bookDao.deleteBook(opt.get());
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book deleted successfully!");
			response.setData("Deleted book with ID: " + id);
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Book not found for ID: " + id);
		}
	}

	// Find by author
	public ResponseEntity<ResponseStructure<List<Book>>> findByAuthor(String author) {
		List<Book> books = bookDao.findByAuthor(author);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books by author " + author + " found!");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found by author " + author);
		}
	}

	// Find by author and genre
	public ResponseEntity<ResponseStructure<List<Book>>> findByAuthorAndGenre(String author, String genre) {
		List<Book> books = bookDao.findByAuthorAndGenre(author, genre);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books found by author " + author + " and genre " + genre);
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found by author " + author + " and genre " + genre);
		}
	}

	// Find books with price greater than
	public ResponseEntity<ResponseStructure<List<Book>>> findByPriceGreaterThan(Double price) {
		List<Book> books = bookDao.findByPriceGreaterThan(price);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books with price greater than " + price + " found!");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found with price greater than " + price);
		}
	}

	// Find books with price less than
	public ResponseEntity<ResponseStructure<List<Book>>> findByPriceLessThan(Double price) {
		List<Book> books = bookDao.findByPriceLessThan(price);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books with price less than " + price + " found!");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found with price less than " + price);
		}
	}

	// Find books between price range
	public ResponseEntity<ResponseStructure<List<Book>>> findByPriceBetween(Double startPrice, Double endPrice) {
		List<Book> books = bookDao.findByPriceBetween(startPrice, endPrice);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books with price between " + startPrice + " and " + endPrice + " found!");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException(
					"No books found with price between " + startPrice + " and " + endPrice);
		}
	}

	// Find books published in 1960
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByPublishedYear() {
		List<Book> books = bookDao.getBooksByPublishedYear();
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books published in year 1960 found!");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found for year 1960");
		}
	}

	// Find by availability
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByAvailability(Boolean availability) {
		List<Book> books = bookDao.getBooksByAvailability(availability);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books with availability = " + availability + " found!");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found with availability = " + availability);
		}
	}

	// Find by genre
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGenre(String genre) {
		List<Book> books = bookDao.getBooksByGenre(genre);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books with genre " + genre + " found!");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found with genre " + genre);
		}
	}
}
