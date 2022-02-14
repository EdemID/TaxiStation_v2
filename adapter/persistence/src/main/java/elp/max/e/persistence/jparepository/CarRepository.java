package elp.max.e.persistence.jparepository;

import elp.max.e.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByNumberCar(String number);

    @Query(value = "SELECT * FROM Car WHERE busy = false LIMIT 1", nativeQuery = true)
    Car findFreeCar();
}
