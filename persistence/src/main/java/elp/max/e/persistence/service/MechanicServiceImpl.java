package elp.max.e.persistence.service;

import elp.max.e.domain.Mechanic;
import elp.max.e.persistence.exception.EntityNotFoundException;
import elp.max.e.persistence.jparepository.MechanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MechanicServiceImpl {

    private final MechanicRepository mechanicRepository;

    public Mechanic findById(Long id) {
        Mechanic mechanicEntity = null;
        if (mechanicRepository.findById(id).isPresent()) {
            mechanicEntity = mechanicRepository.findById(id).get();
        }
        return mechanicEntity;
    }

    public List<Mechanic> findAll() {
        return null;
    }

    public Mechanic save(Mechanic mechanic) {
        return null;
    }

    public Mechanic update(Long id, Mechanic mechanic) {
        Mechanic mechanicEntity = mechanicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Механик " + mechanic + " не найден"));

        mechanicEntity.setRepairTime(mechanic.getRepairTime());
        mechanicEntity.setResource(mechanic.getResource());
        mechanicEntity.setBusy(mechanic.isBusy());
        mechanicEntity = mechanicRepository.save(mechanicEntity);

        return mechanicEntity;

    }

    public void delete(Long id) {

    }
}
