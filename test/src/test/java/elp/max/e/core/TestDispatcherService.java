package elp.max.e.core;

import elp.max.e.domain.Dispatcher;
import elp.max.e.persistence.service.DispatcherServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestDispatcherService {

    DispatcherServiceImpl dispatcherService;
    @Autowired
    public TestDispatcherService(DispatcherServiceImpl dispatcherService) {
        this.dispatcherService = dispatcherService;
    }

    @Test
    public void findWorkingDispatcher() {
        Dispatcher dispatcher = dispatcherService.findWorkingDispatcher();
        Assertions.assertEquals("volodya", dispatcher.getName());
    }
}
