package elp.max.e.persistence.jparepository;

import elp.max.e.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByName(String name);

    @Query("SELECT driver FROM Driver driver WHERE (driver.busy = false) AND (driver.dayoff <> :dayOfWeek)")
    Driver findFreeAndWorkingDriver(String dayOfWeek);
}
