package fi.margokomarova.jdbc_spring_example.controller;

import fi.margokomarova.jdbc_spring_example.model.Book;
import fi.margokomarova.jdbc_spring_example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    public BookController (BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @GetMapping("/book/all")
    List<Book> getAllBooks(){
        return bookRepository.findAllBooks();
    }
}
