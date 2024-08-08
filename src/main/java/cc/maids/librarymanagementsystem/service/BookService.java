package cc.maids.librarymanagementsystem.service;

import cc.maids.librarymanagementsystem.DTO.BookDTOPost;
import cc.maids.librarymanagementsystem.DTO.BookDTOPut;
import cc.maids.librarymanagementsystem.exception.ResourceNotFoundException;
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
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(int id , Book updatedBook) {

        if (bookRepository.existsById(id)){
           Book book = bookRepository.findById(id).get();
            book.setTitle(updatedBook.getTitle() != null ? updatedBook.getTitle() : book.getTitle());
            book.setAuthor(updatedBook.getAuthor() != null ? updatedBook.getAuthor() : book.getAuthor());
            book.setISBN(updatedBook.getISBN() != null ? updatedBook.getISBN() : book.getISBN());
            book.setPublicationYear(updatedBook.getPublicationYear() != 0 ? updatedBook.getPublicationYear() : book.getPublicationYear());

            return bookRepository.save(book);
        }else {
            throw new ResourceNotFoundException(id , "The Book You are trying to update is not exists");
        }
    }

    @Transactional
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public Book mapToBook(BookDTOPost bookDTOPost) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookDTOPost, Book.class);
    }
    public Book mapToBook(BookDTOPut bookDTOPut) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bookDTOPut, Book.class);
    }


}
