package cc.maids.librarymanagementsystem.service;

import cc.maids.librarymanagementsystem.DTO.PatronDTOPost;
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
    public void updatePatron(Patron patron) {
        patronRepository.save(patron);
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

    public boolean isPatronExist(int id) {
        return patronRepository.existsById(id);
    }
}
