package elp.max.e.persistence.service;

import elp.max.e.domain.Car;
import elp.max.e.persistence.exception.EntityNotFoundException;
import elp.max.e.persistence.jparepository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CarServiceImpl {

    private final CarRepository carRepository;

    public List<Car> findAll() {
        log.info("Find all cars.");
        return new ArrayList<>(carRepository.findAll());
    }

    public Car findById(Long id) {
        log.info("Car search by id.");
        Car car = null;
        if (carRepository.findById(id).isPresent()) {
            log.info("Car with id={} found!", id);
            car = carRepository.findById(id).get();
        } else {
            log.info("Car with id={} not found!", id);
            throw new EntityNotFoundException("Автомобиль " + id + " не найден!");
        }
        return car;
    }

    public Car findByNumberCar(String number) {
        Car car;
        if (carRepository.findByNumberCar(number) != null) {
            car = carRepository.findByNumberCar(number);
        } else {
            throw new EntityNotFoundException("Автомобиль " + number + " не найден!");
        }
        return car;
    }

    public Car save(Car car) {
        log.info("Save the car.");
        log.info("Car saved: {}!", car);
        return carRepository.save(car);
    }

    public Car update(Long id, Car car) {
        Car carEntity = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Автомобиль " + car + " не найден"));
        carEntity.setNumberCar(car.getNumberCar());
        carEntity.setResource(car.getResource());
        carEntity.setBusy(car.isBusy());
        carEntity = carRepository.save(carEntity);
        log.info("Update the car with id: {}.", id);
        log.info("Car updated: {}!", car);
        return carEntity;
    }

    @Transactional
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}
