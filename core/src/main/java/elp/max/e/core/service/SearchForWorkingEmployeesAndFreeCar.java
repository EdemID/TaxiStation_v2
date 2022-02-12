package elp.max.e.core.service;

import elp.max.e.domain.Car;
import elp.max.e.domain.Dispatcher;
import elp.max.e.domain.Driver;
import elp.max.e.persistence.service.CarServiceImpl;
import elp.max.e.persistence.service.DispatcherServiceImpl;
import elp.max.e.persistence.service.DriverServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
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
