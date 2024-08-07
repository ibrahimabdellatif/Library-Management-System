package cc.maids.librarymanagementsystem.service;

import cc.maids.librarymanagementsystem.DTO.BookDTOPost;
import cc.maids.librarymanagementsystem.model.Book;
import cc.maids.librarymanagementsystem.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public void addBook(Book book) {
        bookRepository.save(book);
    }
    @Transactional
    public void updateBook(Book book) {
        bookRepository.save(book);
    }
    @Transactional
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public Book mapToBook(BookDTOPost bookDTOPost) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookDTOPost, Book.class);
    }

    public boolean isBookExist(int id) {
        return bookRepository.existsById(id);
    }

}
