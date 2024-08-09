package cc.maids.librarymanagementsystem.service;

import cc.maids.librarymanagementsystem.DTO.PatronDTOPost;
import cc.maids.librarymanagementsystem.DTO.PatronDTOPut;
import cc.maids.librarymanagementsystem.exception.UserNotFoundException;
import cc.maids.librarymanagementsystem.model.Patron;
import cc.maids.librarymanagementsystem.repository.PatronRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(int id) {
        return patronRepository.findById(id);
    }

    @Transactional
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Transactional
    public Patron updatePatron(int id, Patron updatedPatron) {
        if (patronRepository.existsById(id)) {
            Patron patron = patronRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

            patron.setName(updatedPatron.getName() != null ? updatedPatron.getName() : patron.getName());
            patron.setPhone(updatedPatron.getPhone() != null ? updatedPatron.getPhone() : patron.getPhone());
            patron.setEmail(updatedPatron.getEmail() != null ? updatedPatron.getEmail() : patron.getEmail());

            return patronRepository.save(patron);
        } else {
            throw new UserNotFoundException(id);
        }

    }

    @Transactional
    public boolean deletePatron(int id) {
        if (patronRepository.existsById(id)) {
            patronRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Patron mapToPatron(PatronDTOPost patronDTOPost) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(patronDTOPost, Patron.class);
    }

    public Patron mapToPatron(PatronDTOPut patronDTOPut) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(patronDTOPut, Patron.class);
    }
}
