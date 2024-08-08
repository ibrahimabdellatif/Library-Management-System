package cc.maids.librarymanagementsystem.controller;


import cc.maids.librarymanagementsystem.DTO.BookDTOPost;
import cc.maids.librarymanagementsystem.DTO.BookDTOPut;
import cc.maids.librarymanagementsystem.exception.ResourceNotFoundException;
import cc.maids.librarymanagementsystem.model.Book;
import cc.maids.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public void addBook(@Valid @RequestBody BookDTOPost bookDTOPost) {
        Book book = bookService.mapToBook(bookDTOPost);
        bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookService.deleteBook(id);
        System.out.println("Book with id " + id + " deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable int id, @Valid @RequestBody BookDTOPut updatedBook) {
        Book book = bookService.mapToBook(updatedBook);

        try {
            bookService.updateBook(id , book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }catch(ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        }
    }



