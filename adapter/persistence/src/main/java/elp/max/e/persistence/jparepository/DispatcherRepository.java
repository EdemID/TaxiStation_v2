package elp.max.e.persistence.jparepository;

import elp.max.e.model.Dispatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface DispatcherRepository extends JpaRepository<Dispatcher, Long> {
    Dispatcher findByName(String name);

    @Query(value = "SELECT * FROM Dispatcher WHERE ((start_lunch > :currentTime AND :currentTime >= '00:00') OR ('23:59' >= :currentTime AND :currentTime > end_lunch)) AND (dayoff <> :dayOfWeek) LIMIT 1", nativeQuery = true)
    Dispatcher findWorkingDispatcher(LocalTime currentTime, String dayOfWeek);
}
