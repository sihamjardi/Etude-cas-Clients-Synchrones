package ma.siham.clients;
import ma.siham.modeles.Vehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Client Feign déclaratif
 * Appelle le microservice VEHICLE-SERVICE
 */
@FeignClient(name = "VEHICLE-SERVICE")
public interface VehicleFeignClient {

    /**
     * Appel HTTP GET vers VEHICLE-SERVICE
     */
    @GetMapping("/api/vehicles/byUser/{userId}")
    Vehicle getVehicleByUser(@PathVariable("userId") Long userId);
}
