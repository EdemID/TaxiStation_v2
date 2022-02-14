package elp.max.e.core.service;

import elp.max.e.core.exception.WorkingDtoNotFoundException;
import elp.max.e.model.Car;
import elp.max.e.model.Dispatcher;
import elp.max.e.model.Driver;
import elp.max.e.persistence.service.CarServiceImpl;
import elp.max.e.persistence.service.DispatcherServiceImpl;
import elp.max.e.persistence.service.DriverServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SearchForWorkingEmployeesAndFreeCar {

    private DispatcherServiceImpl dispatcherService;
    private DriverServiceImpl driverService;
    private CarServiceImpl carService;

    Dispatcher getWorkingDispatcher() {
        Dispatcher dispatcher = dispatcherService.findWorkingDispatcher();
        if (dispatcher == null) {
            throw new WorkingDtoNotFoundException("Диспетчеры");
        }
        log.info("Dispatcher with id={} working and not busy", dispatcher.getId());
        dispatcher.setWorkStatus(true);
        dispatcherService.save(dispatcher);
        return dispatcher;
    }

    Car getFreeCar() {
        Car car = carService.findFreeCar();
        if (car == null) {
            log.info("Not found a working car");
            throw new WorkingDtoNotFoundException("Автомобили");
        }
        log.info("Found a free car with number={}", car.getNumberCar());
        int currentResource = car.getResource();
        car.setResource(currentResource - 1);
        carService.save(car);
        log.info("Car with id={} resource: {}", car.getId(), car.getResource());
        return car;
    }

    Driver getFreeAndWorkingDriver() {
        Driver driver = driverService.findFreeAndWorkingDriver();
        if (driver == null) {
            log.info("Not found a working driver");
            throw new WorkingDtoNotFoundException("Водители");
        }
        log.info("Found a working and not busy driver with name={}", driver.getName());
        driver.setBusy(true);
        driver.setCar("Назначается машина");
        driverService.save(driver);
        log.info("Driver with id={} car: {}", driver.getId(), driver.getCar());
        return driver;
    }
}
