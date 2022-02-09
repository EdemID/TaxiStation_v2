package elp.max.e.persistence.jparepository;

import elp.max.e.domain.OrderNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderNumberRepository extends JpaRepository<OrderNumber, Long> {
}
