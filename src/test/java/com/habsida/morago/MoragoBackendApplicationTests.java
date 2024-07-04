package com.habsida.morago;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = MoragoBackendApplication.class)
class MoragoBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
