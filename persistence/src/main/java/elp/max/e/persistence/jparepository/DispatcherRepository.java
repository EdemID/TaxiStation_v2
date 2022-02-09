package elp.max.e.persistence.jparepository;

import elp.max.e.domain.Dispatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatcherRepository extends JpaRepository<Dispatcher, Long> {
    Dispatcher findByName(String name);
}
