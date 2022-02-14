package elp.max.e.persistence.service;

import elp.max.e.model.Client;
import elp.max.e.persistence.exception.EntityNotFoundException;
import elp.max.e.persistence.jparepository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl {

    private final ClientRepository clientRepository;

    public List<Client> findAll() {
        log.info("Find all clients.");
        return new ArrayList<>(clientRepository.findAll());
    }

    public Client findById(Long clientId) throws EntityNotFoundException {
        System.out.println("21312432423");
        log.info("Client search by id.");
        Client clientEntity;
        if (clientRepository.findById(clientId).isPresent()) {
            log.info("Client with id={} found!", clientId);
            clientEntity = clientRepository.findById(clientId).get();
        } else {
            log.info("Client with id={} not found!", clientId);
            throw new EntityNotFoundException("Клиент " + clientId + " не найден!");
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        return clientEntity;
    }

    public Client save(Client entity) {
        Client clientEntity = clientRepository.save(entity);
        log.info("Save the client.");
        log.info("Client saved: {}!", entity);
        return clientEntity;
    }

    public Client update(Long id, Client entity) {
        Client clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент " + entity + " не найден!"));
        clientEntity.setName(entity.getName());
        clientEntity.setOrderNumber(entity.getOrderNumber());
        clientEntity = clientRepository.save(clientEntity);
        log.info("Update the client with id: {}.", id);
        log.info("Client updated: {}!", entity);
        return clientEntity;
    }

    public void delete(Long id) {
    }
}
