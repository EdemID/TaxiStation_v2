package elp.max.e.persistence.service;

import elp.max.e.domain.OrderNumber;
import elp.max.e.persistence.jparepository.OrderNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderNumberServiceImpl {

    private final OrderNumberRepository orderNumberRepository;

    public List<OrderNumber> findAll() {
        return null;
    }

    public OrderNumber findById(Long id) {
        return null;
    }

    public OrderNumber save(OrderNumber orderNumber) {
        return orderNumberRepository.save(orderNumber);
    }

    public OrderNumber update(Long id, OrderNumber orderNumber) {
        return null;
    }

    public void delete(Long id) {

    }
}
