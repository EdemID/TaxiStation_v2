package elp.max.e.core.service;

import elp.max.e.core.exception.CallBeforeCompletionOfOrderException;
import elp.max.e.core.exception.WorkingDtoNotFoundException;
import elp.max.e.domain.*;
import elp.max.e.infrastructure.utils.Utils;
import elp.max.e.persistence.service.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BusinessLogic {

    SearchForWorkingEmployees searchForWorkingEmployees;
    CarRepair carRepair;
    OrderNumberServiceImpl orderNumberService;
    DispatcherServiceImpl dispatcherService;
    MechanicServiceImpl mechanicService;
    ClientServiceImpl clientService;
    DriverServiceImpl driverService;
    CarServiceImpl carService;

    private OrderNumber assignCarToDriverAndCallClient(Client client, Dispatcher dispatcher, ClientServiceImpl clientService) throws Exception {
        Driver driver = searchForWorkingEmployees.getWorkingDriver();
        if (driver == null) {
            //////logger.info("Not found a working driver");
            throw new WorkingDtoNotFoundException("Водители");
        }
        //////logger.info("Found a working driver with name={}", driver.getName());

        Car car = searchForWorkingEmployees.getWorkingCar();
        if (car == null) {
            //////logger.info("Not found a working car");
            throw new WorkingDtoNotFoundException("Автомобили");
        }
        //////logger.info("Found a working car with number={}", car.getNumberCar());

        //заняты клиентом
        driver.setBusy(true);
        //////logger.info("Working driver with name={} is busy with client who has an name={}", driver.getName(), client.getName());
        car.setBusy(true);
        //////logger.info("Working car with number={} is busy with client who has an name={}", car.getNumberCar(), client.getName());

        driver.setCar(car.getNumberCar());
        //////logger.info("Assign car={} to driver={}", car.getNumberCar(), driver.getName());

        driver = driverService.update(driver.getId(), driver);
        car = carService.update(car.getId(), car);

        OrderNumber orderNumber = new OrderNumber();
        orderNumber.setNumber(Utils.randomizer());
        orderNumber.setClient(client.getName());
        orderNumber.setDispatcher(dispatcher.getName());
        orderNumber.setDriver(driver.getName());
        orderNumber.setCar(car.getNumberCar());

        // исходя из каких-то данных, диспетчер будет знать время заказа
        releaseClientAndDriverAndCarAfterOrdering(driver, car, client, clientService, 20000L);

        return orderNumberService.save(orderNumber);
    }

    public void releaseClientAndDriverAndCarAfterOrdering(Driver driver, Car car, Client client, ClientServiceImpl clientService, long orderTime) {
        ////logger.info("Метод releaseDriverAndCarAfterOrdering запущен: {}", new Date());
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ////logger.info("Задача \"Врема заказа\" запущена: {}", new Date());

                driver.setBusy(false);
                driver.setCar("free");

                car.setBusy(false);

                client.setOrderNumber("No order");

                driverService.update(driver.getId(), driver);
                carService.update(car.getId(), car);
                clientService.update(client.getId(), client);

                // проверяем ресурс и отправляем после заказа машину, если равен 0
                if (car.getResource() == 0) {
                    ////logger.info("Автомоболь с id={} и ресурсом={} отправлен на ремонт после заказа", car.getId(), car.getResource());
                    carRepair.sendCarForRepair(car, carService, mechanicService);
                }
                ////logger.info("Конец заказа: {}", new Date());

            }
        };
        Timer timer = new Timer("Врема заказа");
        ////logger.info("Start timer \"Врема заказа\" in: {} mc", orderTime);
        ////logger.info("Начало заказа: {}", new Date());
        timer.schedule(task, orderTime);
    }

    public OrderNumber call(Long id) throws Exception {
        //logger.info("Taxi call.");
        //logger.info("Taxi call for client with id: {}!", id);
        //для гет-запроса, для пост-запроса возможно будет передаваться в параметрах
        Client client = clientService.findById(id);

        if (!client.getOrderNumber().equals("No order")) {
            //logger.info("The client called a taxi before completion. His order: {}", client.getOrderNumber());
            throw new CallBeforeCompletionOfOrderException(client.getOrderNumber());
        }

        Dispatcher dispatcher = searchForWorkingEmployees.getWorkingDispatcher();
        OrderNumber orderNumber = assignCarToDriverAndCallClient(client, dispatcher, clientService);
        client.setOrderNumber(orderNumber.getNumber());
        clientService.save(client);

        //logger.info(
////                "Order data:" + System.lineSeparator() +
////                        "-- Order id: {}" + System.lineSeparator() +
////                        "-- Order number: {}" + System.lineSeparator() +
////                        "-- Client name: {}" + System.lineSeparator() +
////                        "-- Dispatcher name: {}" + System.lineSeparator() +
////                        "-- Driver name: {}" + System.lineSeparator() +
////                        "-- Car number: {}" + System.lineSeparator(),
////                orderNumber.getId(),
////                orderNumber.getNumber(),
////                orderNumber.getClient(),
////                orderNumber.getDispatcher(),
////                orderNumber.getDriver(),
////                orderNumber.getCar()
////        );
        return orderNumber;
    }
}