package elp.max.e.persistence.service;

import elp.max.e.domain.Driver;
import elp.max.e.persistence.exception.EntityNotFoundException;
import elp.max.e.persistence.jparepository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DriverServiceImpl {
    
    private final DriverRepository driverRepository;

    public List<Driver> findAll() {
        return new ArrayList<>(driverRepository.findAll());
    }

    public Driver findById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Водитель " + id + " не найден"));
    }

    public Driver findByName(String name) {
        Driver driverEntity = driverRepository.findByName(name);
        if (driverEntity == null) {
            throw new EntityNotFoundException("Водитель " + name + " не найден");
        }
        return driverEntity;
    }

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver update(Long id, Driver driver) {
        Driver driverEntity = driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Водитель " + driver + " не найден"));
        driverEntity.setName(driver.getName());
        driverEntity.setWorkStatus(driver.isWorkStatus());
        driverEntity.setCar(driver.getCar());
        driverEntity.setDayoff(driver.getDayoff());
        driverEntity.setBusy(driver.isBusy());
        driverEntity = driverRepository.save(driverEntity);
        return driverEntity;
    }

    public void delete(Long id) {

    }
}
