package ma.siham.controllers;

import ma.siham.clients.VehicleFeignClient;
import ma.siham.modeles.Vehicle;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Contrôleur REST du microservice USER-SERVICE
 *
 * Expose 3 endpoints pour comparer :
 * - RestTemplate
 * - Feign
 * - WebClient
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RestTemplate restTemplate;
    private final VehicleFeignClient feignClient;
    private final WebClient.Builder webClientBuilder;

    public UserController(RestTemplate restTemplate,
                          VehicleFeignClient feignClient,
                          WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.feignClient = feignClient;
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Appel synchrone via RestTemplate
     */
    @GetMapping("/{id}/vehicle/rest")
    public Vehicle getVehicleViaRest(@PathVariable Long id) {
        return restTemplate.getForObject(
                "http://VEHICLE-SERVICE/api/vehicles/byUser/" + id,
                Vehicle.class
        );
    }

    /**
     * Appel via Feign (déclaratif)
     */
    @GetMapping("/{id}/vehicle/feign")
    public Vehicle getVehicleViaFeign(@PathVariable Long id) {
        return feignClient.getVehicleByUser(id);
    }

    /**
     * Appel via WebClient (réactif bloqué)
     */
    @GetMapping("/{id}/vehicle/webclient")
    public Vehicle getVehicleViaWebClient(@PathVariable Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://VEHICLE-SERVICE/api/vehicles/byUser/" + id)
                .retrieve()
                .bodyToMono(Vehicle.class)
                .block(); // blocage volontaire pour comparaison
    }
}
