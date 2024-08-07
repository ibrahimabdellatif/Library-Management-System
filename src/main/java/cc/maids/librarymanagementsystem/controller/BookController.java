package cc.maids.librarymanagementsystem.controller;


import cc.maids.librarymanagementsystem.DTO.BookDTOPost;
import cc.maids.librarymanagementsystem.DTO.BookDTOPut;
import cc.maids.librarymanagementsystem.exception.ResourceNotFoundException;
import cc.maids.librarymanagementsystem.model.Book;
import cc.maids.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/books")
    List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    Optional<Book> getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    void addBook(@Valid @RequestBody BookDTOPost bookDTOPost) {
        Book book = bookService.mapToBook(bookDTOPost);
        bookService.addBook(book);
    }

    @DeleteMapping("/books/{id}")
    void deleteBookById(@PathVariable int id) {
        bookService.deleteBook(id);
        System.out.println("Book with id " + id + " deleted successfully");
    }

    @PutMapping("/books/{id}")
    ResponseEntity<Book> updateBookById(@PathVariable int id, @Valid @RequestBody BookDTOPut updatedBook) {
        Book book = bookService.getBookById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Book"));
        book.setTitle(updatedBook.getTitle() != null ? updatedBook.getTitle() : book.getTitle());
        book.setAuthor(updatedBook.getAuthor() != null ? updatedBook.getAuthor() : book.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear() != 0 ? updatedBook.getPublicationYear() : book.getPublicationYear());
        book.setISBN(updatedBook.getIsbn() != null ? updatedBook.getIsbn() : book.getISBN());

        bookService.updateBook(book);
        return ResponseEntity.ok(book);

    }


}
