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

		return new ResponseEntity<ResponseStructure<List<Book>>>(HttpStatus.CREATED);
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
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Book not found for ID: " + id);
			response.setData(null);
			return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.NOT_FOUND);
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
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Book not found for update!");
			response.setData(null);
			return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.NOT_FOUND);
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
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setMessage("Book not found with ID: " + id);
			response.setData(null);
			return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
		}

	}
}
