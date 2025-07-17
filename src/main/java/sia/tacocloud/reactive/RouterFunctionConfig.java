package sia.tacocloud.reactive;


//Commit TacoController <------


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import sia.tacocloud.Taco;
import sia.tacocloud.data.TacoRepository;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctionConfig {

    @Autowired
    private TacoRepository tacoRepo;

    @Bean
    public RouterFunction<?> routerFunction () {
        return route(GET("/api/tacos").and(queryParam("recent", t -> t != null)), this:: recent)
                .andRoute(POST("/api/tacos"), this::postTaco);
    }

    public Mono<ServerResponse> recent (ServerRequest request) {
        return ServerResponse.ok()
                .body(tacoRepo.findAll().take(12), Taco.class);
    }

    public Mono<ServerResponse> postTaco (ServerRequest request) {
        return request.bodyToMono(Taco.class)
                .flatMap(taco -> tacoRepo.save(taco))
                .flatMap(savedTaco -> {
                        return ServerResponse.
                                created(URI.create("http://localhost:8080/api/tacos" + savedTaco.getId()))
                                .body(savedTaco, Taco.class); });

    }
}
