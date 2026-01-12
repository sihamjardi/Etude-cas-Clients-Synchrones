package ma.siham.controlle;

import ma.siham.modeles.Vehicle;
import org.springframework.web.bind.annotation.*;

/**
 * API REST du microservice VEHICLE
 */
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    /**
     * Endpoint utilisé pour les tests de performance
     */
    @GetMapping("/byUser/{userId}")
    public Vehicle getVehicleByUser(@PathVariable Long userId) throws InterruptedException {

        // Simulation de latence (20 ms)
        Thread.sleep(20);

        return new Vehicle(
                1L,
                "Toyota",
                "Yaris",
                userId
        );
    }
}
