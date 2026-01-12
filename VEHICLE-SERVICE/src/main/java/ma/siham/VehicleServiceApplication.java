package ma.siham;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe principale du microservice VEHICLE-SERVICE
 *
 * Rôles :
 * - Démarre l'application Spring Boot
 * - Active l'enregistrement du service auprès du serveur de découverte
 *   (Eureka ou Consul)
 */
@SpringBootApplication
@EnableDiscoveryClient
public class VehicleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleServiceApplication.class, args);
    }
}
