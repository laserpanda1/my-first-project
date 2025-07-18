package sia.tacocloud;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TacoControllerWebTest {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void shoulReturnRecentTacos() throws Exception {
        testClient.get().uri("/api/tacos?/recent")
                .accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(3)
                .jsonPath("$[?(@.name == 'Carnivore')]").exists()
                .jsonPath("$[?(@.name == 'Bovine Bounty')]").exists()
                .jsonPath("$[?@.name == 'Veg-out')]").exists();
    }
}
