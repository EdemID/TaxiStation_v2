package elp.max.e.persistence.service;

import elp.max.e.domain.Dispatcher;
import elp.max.e.persistence.exception.EntityNotFoundException;
import elp.max.e.persistence.jparepository.DispatcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DispatcherServiceImpl {

    private final DispatcherRepository dispatcherRepository;

    public List<Dispatcher> findAll() {
        return new ArrayList<>(dispatcherRepository.findAll());
    }

    public Dispatcher findById(Long id) {
        return dispatcherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Диспетчер " + id + " не найден"));
    }

    public Dispatcher findByName(String name) {
        Dispatcher dispatcherEntity = dispatcherRepository.findByName(name);
        if (dispatcherEntity == null) {
            throw new EntityNotFoundException("Диспетчер " + name + " не найден");
        }
        return dispatcherEntity;
    }

    public Dispatcher save(Dispatcher dispatcher) {
        return dispatcherRepository.save(dispatcher);
    }

    public Dispatcher update(Long id, Dispatcher dispatcher) {
        return null;
    }

    public void delete(Long dispatcherId) {

    }

}
