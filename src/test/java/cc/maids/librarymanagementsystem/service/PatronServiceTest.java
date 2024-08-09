package cc.maids.librarymanagementsystem.service;

import cc.maids.librarymanagementsystem.exception.UserNotFoundException;
import cc.maids.librarymanagementsystem.model.Patron;
import cc.maids.librarymanagementsystem.repository.PatronRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PatronServiceTest {


    @InjectMocks
    private PatronService patronService;

    @Mock
    private PatronRepository patronRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindPatronById() {
//        Given
        int patronId = 1;
        Patron patron = new Patron();
        patron.setId(patronId);
//        Mock the behaviour of patronRepository and what it should return if it called findById Method.
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));

//        When
        Patron result = patronService.getPatronById(patronId).orElseThrow(() -> new UserNotFoundException(patronId));

//        then
        assertNotNull(result);
        assertEquals(patronId, result.getId());

        verify(patronRepository).findById(patronId);
    }

    @Test
    public void testFindAllPatrons() {
//        Given
        Patron patron = new Patron();
        patron.setId(1);
        Patron patron2 = new Patron();
        patron2.setId(2);

        List<Patron> patronsList = Arrays.asList(patron, patron2);

        when(patronRepository.findAll()).thenReturn(patronsList);

//        when
        List<Patron> result = patronService.getAllPatrons();

//        Then

        assertNotNull(result);

        assertEquals(2, result.size());
        assertEquals(patron.getId(), result.get(0).getId());
        assertEquals(patron2.getId(), result.get(1).getId());
        assertEquals(result, patronsList);

        verify(patronRepository).findAll();

    }

    @Test
    public void testSavePatron() {
//        Given
        Patron patron = new Patron();
        patron.setId(1);
        patron.setName("Patron");


        when(patronRepository.save(patron)).thenReturn(patron);

//        when
        Patron patron1 = patronService.addPatron(patron);

//        Then
        assertNotNull(patron1);

        verify(patronRepository, times(1)).save(patron);
    }

    @Test
    public void testUpdatePatron() {
        int patronId = 1;
        Patron patron = new Patron();

        patron.setId(patronId);
        patron.setName("Old Patron");

        Patron updatedPatron = new Patron();
        updatedPatron.setId(patronId);
        updatedPatron.setName("New Patron");

        when(patronRepository.existsById(patronId)).thenReturn(true);
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));
        when(patronRepository.save(any(Patron.class))).thenReturn(patron);

        Patron result = patronService.updatePatron(patronId, updatedPatron);


        assertNotNull(result);
        assertEquals(patronId, result.getId());
        assertEquals("New Patron", result.getName(), "The name of patron is different");

        verify(patronRepository, times(1)).findById(patronId);
        verify(patronRepository, times(1)).save(patron);


    }
}
