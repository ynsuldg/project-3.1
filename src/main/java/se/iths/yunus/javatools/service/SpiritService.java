package se.iths.yunus.javatools.service;

import org.springframework.stereotype.Service;
import se.iths.yunus.javatools.exception.SpiritNotFoundException;
import se.iths.yunus.javatools.model.Spirit;
import se.iths.yunus.javatools.repository.SpiritRepository;
import se.iths.yunus.javatools.validator.SpiritValidator;

import java.util.List;

@Service
public class SpiritService {
    private final SpiritRepository spiritRepository;
    private final SpiritValidator spiritValidator;

    public SpiritService(SpiritRepository spiritRepository, SpiritValidator spiritValidator) {
        this.spiritRepository = spiritRepository;
        this.spiritValidator = spiritValidator;
    }

    //Get all
    public List<Spirit> getAllSpirit() {
        return spiritRepository.findAll();
    }

    public Spirit createSpirit(Spirit spirit) {
        spiritValidator.validated(spirit);
        return spiritRepository.save(spirit);
    }

    //Get en
    public Spirit getSpiritId(Long id) {
        return spiritRepository
                .findById(id).orElseThrow(() -> new SpiritNotFoundException("No Spirit" + id));
    }

    public Spirit updateSpirit(Long id, Spirit spirit) {
        Spirit update = spiritRepository.findById(id)

                .orElseThrow(() -> new SpiritNotFoundException("No spirit can be find " + id));

        spiritValidator.validated(spirit);

        update.setType(spirit.getType());
        update.setTitle(spirit.getTitle());
        update.setApv(spirit.getApv());
        update.setAgeInMonth(spirit.getAgeInMonth());
        update.setPrice(spirit.getPrice());

        return spiritRepository.save(update);
    }

    public void deleteSpirit(Long id) {
        Spirit spirit = spiritRepository.findById(id)
                .orElseThrow(() -> new SpiritNotFoundException("No spirit can be find " + id));
        spiritRepository.delete(spirit);
    }
}
