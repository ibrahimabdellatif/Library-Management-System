package cc.maids.librarymanagementsystem.service;

import cc.maids.librarymanagementsystem.model.Book;
import cc.maids.librarymanagementsystem.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {


    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindBookById() {
//        given
        Book book = new Book();
        book.setId(10);

        when(bookRepository.findById(10)).thenReturn(Optional.of(book));

        //When
        Book foundBook = bookService.getBookById(10).orElse(null);

//        Then
        assertNotNull(foundBook);
        assertEquals(10, foundBook.getId(), "The book id is incorrect");
        verify(bookRepository, times(1)).findById(10);

    }

    @Test
    public void testSaveBook() {
//        given
        Book book = new Book();
        when(bookRepository.save(book)).thenReturn(book);

//        when
        Book savedBook = bookService.addBook(book);

//        Then
        assertNotNull(savedBook);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testDeleteBook() {
//        given
        int bookId = 10;
//        when
        bookService.deleteBook(bookId);

//        then
        verify(bookRepository, times(1)).deleteById(10);

    }

    @Test
    public void testDeleteBookByIdWhenIdDoesNotExist() {
        int bookId = 10;

        doThrow(new IllegalArgumentException("Book is found")).when(bookRepository).deleteById(bookId);

        assertThrows(IllegalArgumentException.class, () -> bookService.deleteBook(bookId));

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    public void testGetAllBook() {
//        given
        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Book one");

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("Book two");

        List<Book> bookList = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(bookList);

//        when
        List<Book> result = bookService.getAllBooks();

//                then
        assertNotNull(bookList, "the result should not be null");

//        check list size
        assertEquals(2, result.size(), "the result size should be 2");
        assertEquals("Book one", result.get(0).getTitle(), "the book title should be the one");
        assertEquals("Book two", result.get(1).getTitle(), "the book title should be the two");

        verify(bookRepository, times(1)).findAll();

    }

    @Test
    public void testUpdateBook() {
        int bookId = 1;

        Book exsistingBook = new Book();
        exsistingBook.setId(bookId);
        exsistingBook.setTitle("Old Title");

        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setTitle("New Title");

        when(bookRepository.existsById(bookId)).thenReturn(true);
        when(bookRepository.save(any(Book.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

//      when
        Book result = bookService.updateBook(bookId,updatedBook);
//      Then
        assertNotNull(result, "the result should not be null");
        assertEquals(bookId, result.getId(), "The book id is incorrect");
        assertEquals("New Title", result.getTitle(), "The updated book title should be 'New Title'");

        verify(bookRepository, times(1)).existsById(bookId);
        verify(bookRepository, times(1)).save(updatedBook);
    }


}
