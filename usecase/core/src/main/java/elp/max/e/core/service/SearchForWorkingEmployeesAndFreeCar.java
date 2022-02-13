package elp.max.e.core.service;

import elp.max.e.model.Car;
import elp.max.e.model.Dispatcher;
import elp.max.e.model.Driver;
import elp.max.e.persistence.service.CarServiceImpl;
import elp.max.e.persistence.service.DispatcherServiceImpl;
import elp.max.e.persistence.service.DriverServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchForWorkingEmployeesAndFreeCar {

    private DispatcherServiceImpl dispatcherService;
    private DriverServiceImpl driverService;
    private CarServiceImpl carService;

    Dispatcher getWorkingDispatcher() {
        return dispatcherService.findWorkingDispatcher();
    }

    Car getFreeCar() {
        return carService.findFreeCar();
    }

    Driver getFreeAndWorkingDriver() {
        return driverService.findFreeAndWorkingDriver();
    }
}
