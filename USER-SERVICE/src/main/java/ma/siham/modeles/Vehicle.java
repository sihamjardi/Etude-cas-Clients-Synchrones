package ma.siham.modeles;

/**
 * Objet DTO représentant un véhicule
 * Reçu depuis le microservice VEHICLE-SERVICE
 */
public class Vehicle {

    private Long id;
    private String brand;
    private String model;
    private Long userId;

    public Vehicle() {
    }

    public Vehicle(Long id, String brand, String model, Long userId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.userId = userId;
    }

    // Getters et setters
}
