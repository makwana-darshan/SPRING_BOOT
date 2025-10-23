package com.jsp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.GeneratedValue;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@PostMapping("/book")
	public String saveBook(@RequestBody Book book) {
		bookRepository.save(book);
		return " data is saved..!";
	}
	
	@PostMapping("/bookall")
	public String saveAllBook(@RequestBody List<Book> books) {
		bookRepository.saveAll(books);
		return " data is saved..!";
	}
	
	@GetMapping("/book")
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
}
