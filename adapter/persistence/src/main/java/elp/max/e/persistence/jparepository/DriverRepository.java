package elp.max.e.persistence.jparepository;

import elp.max.e.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByName(String name);

    @Query(value = "SELECT * FROM Driver WHERE (busy = false) AND (dayoff <> :dayOfWeek) LIMIT 1", nativeQuery = true)
    Driver findFreeAndWorkingDriver(String dayOfWeek);
}
