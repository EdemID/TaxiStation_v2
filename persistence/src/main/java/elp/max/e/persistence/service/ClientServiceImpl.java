package elp.max.e.persistence.service;

import elp.max.e.domain.Client;
import elp.max.e.persistence.exception.EntityNotFoundException;
import elp.max.e.persistence.jparepository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    public List<Client> findAll() {
        logger.info("Find all clients.");
        return new ArrayList<>(clientRepository.findAll());
    }

    public Client findById(Long clientId) throws EntityNotFoundException {
        logger.info("Client search by id.");
        Client clientEntity;
        if (clientRepository.findById(clientId).isPresent()) {
            logger.info("Client with id={} found!", clientId);
            clientEntity = clientRepository.findById(clientId).get();
        } else {
            logger.info("Client with id={} not found!", clientId);
            throw new EntityNotFoundException("Клиент " + clientId + " не найден!");
        }
        return clientEntity;
    }

    public Client save(Client entity) {
        Client clientEntity = clientRepository.save(entity);
        logger.info("Save the client.");
        logger.info("Client saved: {}!", entity);
        return clientEntity;
    }

    public Client update(Long id, Client entity) {
        Client clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент " + entity + " не найден!"));
        clientEntity.setName(entity.getName());
        clientEntity.setOrderNumber(entity.getOrderNumber());
        clientEntity = clientRepository.save(clientEntity);
        logger.info("Update the client with id: {}.", id);
        logger.info("Client updated: {}!", entity);
        return clientEntity;
    }

    public void delete(Long id) {
    }
}
