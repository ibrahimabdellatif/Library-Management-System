package cc.maids.librarymanagementsystem.controller;

import cc.maids.librarymanagementsystem.DTO.PatronDTOPost;
import cc.maids.librarymanagementsystem.DTO.PatronDTOPut;
import cc.maids.librarymanagementsystem.exception.UserNotFoundException;
import cc.maids.librarymanagementsystem.model.Patron;
import cc.maids.librarymanagementsystem.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@Validated
public class PatronController {

    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public Patron getPatronById(@PathVariable int id) {
        return patronService.getPatronById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    public Patron createPatron(@Valid @RequestBody PatronDTOPost patronDTOPost) {
        Patron patron = patronService.mapToPatron(patronDTOPost);
        return patronService.addPatron(patron);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatronById(@PathVariable int id) {

        boolean isDeleted = patronService.deletePatron(id);

        if (isDeleted) {
//            HttpStatus.NO_CONTENT it will return 204 status code that means the delete operation successful and not return content required
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatronById(@PathVariable int id, @Valid @RequestBody PatronDTOPut patronDTOPut) {
        Patron patron = patronService.mapToPatron(patronDTOPut);
        try {
            Patron result = patronService.updatePatron(id ,patron);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
