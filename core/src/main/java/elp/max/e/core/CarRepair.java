package elp.max.e.core;

import elp.max.e.domain.Car;
import elp.max.e.domain.Mechanic;
import elp.max.e.persistence.service.CarServiceImpl;
import elp.max.e.persistence.service.MechanicServiceImpl;
import lombok.AllArgsConstructor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

@AllArgsConstructor
public class CarRepair {

    void sendCarForRepair(Car carEntity, CarServiceImpl carService, MechanicServiceImpl mechanicService) {
        //logger.info("Car with id={} arrived for repair", carEntity.getId());
        boolean busy = true;
        carEntity.setBusy(busy);
        // хардкор, так как пока у нас 1 механик, в будущем можно также реализовать двух механиков с режимом работы
        Mechanic mechanic = mechanicService.findById(1L);
        carEntity.setMechanic(mechanic);

        carService.save(carEntity);

        /**
         * если создать бин карСервиса, то будет цикл: карСервис <-> механикСервис
         */
        repairCar(mechanic, carEntity, carService, mechanicService);
    }

    public void repairCar(Mechanic mechanic, Car carEntity, CarServiceImpl carService, MechanicServiceImpl mechanicService) {

        //logger.info("Метод repairCar запущен: {}", new Date());
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<TimerRepairCar> callable = new CallableClass(carEntity, carService, mechanic, mechanic, mechanicService);
        Future<TimerRepairCar> future = executor.submit(callable);

        // Выводим в консоль полученное значение
        TimerRepairCar s;
        try {
            s = future.get();
            //logger.info("Результат выполнения нити Callable: {}", s.scheduledExecutionTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Останавливаем пул потоков
        executor.shutdown();

        //logger.info("Car with number car={} under repair", carEntity.getNumberCar());
    }

    @AllArgsConstructor
    private static class CallableClass implements Callable<TimerRepairCar> {

        private Car carEntity;
        private CarServiceImpl carService;
        private Mechanic mechanicEntity;
        private Mechanic mechanic;
        private MechanicServiceImpl mechanicService;

        @Override
        public TimerRepairCar call() {

            long repairTime = mechanicEntity.getRepairTime();

            long repairCompletionTime = System.currentTimeMillis() + repairTime;
            while (repairCompletionTime > System.currentTimeMillis()) {
                if (!mechanicService.findById(mechanic.getId()).isBusy()) {
                    //logger.info("Mechanic free");
                    break;
                }
            }

            mechanic.setBusy(true);
            mechanicService.update(mechanic.getId(), mechanic);
            //logger.info("Mechanic busy");

            TimerRepairCar task = new TimerRepairCar(carEntity, carService, mechanicEntity, mechanic, mechanicService);
            Timer timer = new Timer("Время ремонта");
            timer.schedule(task, repairTime);
            //logger.info("Start timer \"Время ремонта\" in: {} mc", repairTime);
            //logger.info("Начало ремонта: {}", new Date());
            return task;
        }
    }

    @AllArgsConstructor
    private static class TimerRepairCar extends TimerTask {

        private Car carEntity;
        private CarServiceImpl carService;
        private Mechanic mechanicEntity;
        private Mechanic mechanic;
        private MechanicServiceImpl mechanicService;

        @Override
        public void run() {
            //logger.info("Задача \"Время ремонта\" запущена: {}", new Date());

            // как избавиться от механика?
            carEntity.setMechanic(null);
            carEntity.setBusy(false);
            //logger.info("Car resource before repair: {}", carEntity.getResource());
            carEntity.setResource(mechanicEntity.getResource());
            //logger.info("Car resource after repair: {}", carEntity.getResource());
            carService.update(carEntity.getId(), carEntity);

            mechanic.setBusy(false);
            mechanicService.update(mechanic.getId(), mechanic);
            //logger.info("Mechanic freed");
            //logger.info("Конец ремонта: {}", new Date());

        }
    }
}
