package elp.max.e.persistence.jparepository;

import elp.max.e.domain.Dispatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface DispatcherRepository extends JpaRepository<Dispatcher, Long> {
    Dispatcher findByName(String name);

    @Query("SELECT d FROM Dispatcher d WHERE ((d.startLunch > :currentTime AND :currentTime >= '00:00') OR ('23:59' >= :currentTime AND :currentTime > d.endLunch)) AND (d.dayoff <> :dayOfWeek)")
    Dispatcher findWorkingDispatcher(LocalTime currentTime, String dayOfWeek);
}
