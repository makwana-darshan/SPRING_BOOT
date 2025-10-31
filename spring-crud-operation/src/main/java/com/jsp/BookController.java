package com.jsp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	// Create a single book
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
		Book savedBook = bookRepository.save(book);

		ResponseStructure<Book> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Book saved successfully!");
		response.setData(savedBook);

		return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.CREATED);
	}

	// Create multiple books
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Book>>> saveAllBooks(@RequestBody List<Book> books) {
		List<Book> savedBooks = bookRepository.saveAll(books);

		ResponseStructure<List<Book>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("All books saved successfully!");
		response.setData(savedBooks);

		return new ResponseEntity<ResponseStructure<List<Book>>>(HttpStatus.CREATED);
	}

	// Get all books
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {
		List<Book> books = bookRepository.findAll();

		ResponseStructure<List<Book>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("All books fetched successfully!");
		response.setData(books);

		return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.CREATED);
	}

	// Get book by ID
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Integer id) {
		Optional<Book> optionalBook = bookRepository.findById(id);

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
	@PutMapping
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		ResponseStructure<Book> response = new ResponseStructure<>();

		Optional<Book> optional = bookRepository.findById(book.getId());
		if (optional.isPresent()) {
			Book updatedBook = bookRepository.save(book);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book updated successfully!");
			response.setData(updatedBook);
			return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Book not found for ID: ");
		}

	}

	// Delete book by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
		ResponseStructure<String> response = new ResponseStructure<>();

		Optional<Book> opt = bookRepository.findById(id);
		if (opt.isPresent()) {
			bookRepository.delete(opt.get());
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book deleted successfully!");
			response.setData("Deleted book with ID: " + id);
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Book not found for ID: " + id);
		}

	}

	// find by author
	@GetMapping("/author/{author}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthod(@PathVariable String author) {
		List<Book> books = bookRepository.findByAuthor(author);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book record with author " + author + " is found");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("Book record with author " + author + " is not exist");
		}
	}

	// find by author and genre
	@GetMapping("/author/{author}/genre/{genre}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthodAndGenre(@PathVariable String author,
			@PathVariable String genre) {
		List<Book> books = bookRepository.findByAuthorAndGenre(author, genre);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book record with author " + author + " is found");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException(
					"Book record with author " + author + "And Genre is" + genre + " is not exist");
		}
	}

	// find By Price GreaterThan
	@GetMapping("/price/greaterthan/{price}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGreaterThanPrice(@PathVariable Double price) {
		List<Book> books = bookRepository.findByPriceGreaterThan(price);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book record with greaterthan price" + price + " is found");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("Book record with greaterthan price" + price + "  is not exist");
		}
	}

	// find By Price Less Than
	@GetMapping("/price/lessthan/{price}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByLessThanPrice(@PathVariable Double price) {
		List<Book> books = bookRepository.findByPriceLessThan(price);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book record with lessthan price" + price + " is found");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("Book record with lessthan price" + price + "  is not exist");
		}
	}

	// find By Price Between
	@GetMapping("/price/between/{stratprice}/{endprice}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPriceBetween(@PathVariable Double stratprice,
			@PathVariable Double endprice) {
		List<Book> books = bookRepository.findByPriceBetween(stratprice, endprice);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Book record with stratprice " + stratprice + " and endprice" + endprice + " is found");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException(
					"Book record with stratprice " + stratprice + " and endprice" + endprice + " is not found");
		}
	}

	@GetMapping("/year/1960")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByPublishedYear1960() {
		List<Book> books = bookRepository.getBooksByPublishedYear();
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books published in year 1960 found");
			response.setData(books);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found for year 1960");
		}
	}

	@GetMapping("/availability/{availability}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByAvailability(@PathVariable Boolean availability) {
		List<Book> books = bookRepository.getBooksByAvailability(availability);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books with availability = " + availability + " found");
			response.setData(books);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found with availability = " + availability);
		}
	}

	// using Query
	@GetMapping("/genre/{genre}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBooksByGenre(@PathVariable String genre) {
		List<Book> books = bookRepository.getBooksByGenre(genre);
		ResponseStructure<List<Book>> response = new ResponseStructure<>();

		if (!books.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books with genre '" + genre + "' found");
			response.setData(books);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No books found with genre " + genre );
		}
	}

}
