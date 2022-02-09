package elp.max.e.web.controller;

import elp.max.e.core.service.BusinessLogic;
import elp.max.e.domain.Client;
import elp.max.e.domain.OrderNumber;
import elp.max.e.persistence.service.ClientServiceImpl;
import elp.max.e.web.exception.ValidationDtoException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientServiceImpl clientService;
    private final BusinessLogic businessLogic;

    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Client create(@RequestBody Client client) throws ValidationDtoException {
        logger.info("Get-request received with client: {}", client);
        Client savedClient = clientService.save(client);
        logger.info("The result is returned: {}", savedClient);
        return savedClient;
    }

    @PatchMapping(value = "/update/{clientId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Client update(@PathVariable Long clientId, @RequestBody Client client) throws ValidationDtoException {
        logger.info("Get-request received with" + System.lineSeparator() +
                     "clientId: {}" + System.lineSeparator() +
                     "client: {}", clientId, client);
        Client updatedClient = clientService.update(clientId, client);
        logger.info("The result is returned: {}", updatedClient);
        return updatedClient;
    }

    @GetMapping(value = "/all")
        public List<Client> findAll() {
            logger.info("Get-request 'findAll()' received");
            List<Client> clients = clientService.findAll();
            logger.info("The result is returned: {}", clients);
            return clients;
    }

    @GetMapping(value = "/{clientId}", produces = APPLICATION_JSON_VALUE)
    public Client findById(@PathVariable Long clientId) {
        logger.info("Get-request received with clientId: {}", clientId);
        Client client = clientService.findById(clientId);
        logger.info("The result is returned: {}", client);
        return client;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public void delete(@RequestParam Long clientId) {
        clientService.delete(clientId);
    }

    /*
    @PostMapping(value = "/{clientId}/call")
    public String call(@PathVariable Long clientId, @RequestBody Client client) throws Exception {
        return clientService.call(clientId, client);
    }
     */

    @GetMapping(value = "/{clientId}/call", produces = APPLICATION_JSON_VALUE)
    public OrderNumber call(@PathVariable Long clientId) throws Exception {
        logger.info("Get-request received with clientId: {}", clientId);
        OrderNumber orderNumber = businessLogic.call(clientId);
//        logger.info("The result is returned: {}", orderNumber);
//        return orderNumber;
        return orderNumber;
    }

    /*
    Map<String, Object> response = new HashMap<>();
response.put("message", "Test data");
response.put("number", 1)
return ResponseEntity.ok(response);
     */
}
