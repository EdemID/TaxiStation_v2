package elp.max.e.persistence.jparepository;

import elp.max.e.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByNumberCar(String number);

    @Query("SELECT c FROM Car c WHERE c.busy = false")
    Car findFreeCar();
}
