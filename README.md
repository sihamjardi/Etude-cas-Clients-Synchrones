# 📄 Étude de cas : Clients Synchrones (RestTemplate vs Feign vs WebClient) avec Eureka et Consul


---

## 1️⃣ Titre

**Étude de cas : Communication synchrone entre microservices avec RestTemplate, Feign et WebClient**  
**Découverte de services : Eureka et Consul**

---

## 2️⃣ Objectifs pédagogiques

À la fin de ce TP, l’étudiant sera capable de :

1. Créer deux microservices communiquant de manière synchrone.
2. Configurer la **découverte de services** avec Eureka et Consul.
3. Implémenter trois clients HTTP côté **USER-SERVICE** :  
   - RestTemplate  
   - Feign  
   - WebClient (bloqué pour comparaison synchrone)
4. Réaliser des tests de performance (latence / débit) et mesurer l’utilisation CPU/RAM.
5. Tester la **résilience** des services (pannes VEHICLE-SERVICE, discovery server, USER-SERVICE).

---

## 3️⃣ Architecture cible

USER-SERVICE
|--- RestTemplate
|--- Feign
|--- WebClient
|
VEHICLE-SERVICE
|
Discovery Server (Eureka ou Consul)


- USER-SERVICE consomme VEHICLE-SERVICE via **nom logique**.
- VEHICLE-SERVICE expose un endpoint `/api/vehicles/byUser/{id}`.
- USER-SERVICE expose trois endpoints `/api/users/{id}/vehicle/...` pour chaque client.

---

## 4️⃣ Technologies utilisées

| Item | Version / Outil |
|------|----------------|
| Java | 17+ |
| Spring Boot | 3.x |
| Spring Cloud | 2022.0.x |
| Eureka Server | 8761 |
| Consul | 8500 |
| Feign | Spring Cloud OpenFeign |
| WebClient | Spring WebFlux |
| RestTemplate | Spring Web |
| Maven | 3.8+ |
| IDE | IntelliJ / Eclipse |
| Test de charge | JMeter / Postman |
| Optionnel | Docker + Docker Compose |
| Optionnel | Prometheus + Grafana |

---

## 5️⃣ Partie A — VEHICLE-SERVICE

**Fonction :** exposer un endpoint REST pour fournir des données sur un véhicule.

### Endpoint
GET /api/vehicles/byUser/{userId}


### Exemple de réponse JSON
```json
{
  "id": 1,
  "brand": "Toyota",
  "model": "Yaris",
  "userId": 1
}
```

## Notes pédagogiques

Délai simulé de 20ms pour rendre la comparaison entre clients observable.

Pas de base de données : données en mémoire.

## 6️⃣ Partie B — USER-SERVICE

Fonction : consommer VEHICLE-SERVICE via trois méthodes synchrones.

### Endpoints exposés
| Endpoint                            | Client utilisé     |
| ----------------------------------- | ------------------ |
| `/api/users/{id}/vehicle/rest`      | RestTemplate       |
| `/api/users/{id}/vehicle/feign`     | Feign              |
| `/api/users/{id}/vehicle/webclient` | WebClient (bloqué) |

### Résultat attendu

JSON identique pour les trois endpoints.

Permet comparaison équitable.

## 7️⃣ Partie C — Discovery
### Mode Eureka

Lancer Eureka Server sur http://localhost:8761.

Configuration dans application.yml USER-SERVICE et VEHICLE-SERVICE.

Validation : les deux services apparaissent dans l’UI et sont UP.

Appels via nom logique fonctionnent (http://VEHICLE-SERVICE/...).

### Mode Consul

Lancer Consul (consul agent -dev) sur http://localhost:8500.

Remplacer Eureka par spring-cloud-starter-consul-discovery.

Activer health check via Spring Boot Actuator.

Validation : services apparaissent en état passing.

Appels fonctionnent de manière identique.

## 8️⃣ Partie D — Tests de performance
### Scénarios

Tester chaque client (RestTemplate / Feign / WebClient) avec JMeter ou Postman.

Charges simulées : 10 / 50 / 100 / 200 / 500 utilisateurs simultanés.

Mesures :

Temps moyen (ms)

P95 (ms)

Débit (req/s)

### Exemple tableau de performance

| Méthode      | Latence moyenne (ms) | P95 (ms) | Débit (req/s) |
| ------------ | -------------------- | -------- | ------------- |
| RestTemplate |                      |          |               |
| Feign        |                      |          |               |
| WebClient    |                      |          |               |


## 9️⃣ Partie E — Mesures CPU / Mémoire
### Méthodes

Task Manager (Windows) ou htop (Linux)

Observer JAVA processes de USER-SERVICE et VEHICLE-SERVICE

Option avancée : Spring Boot Actuator + Prometheus/Grafana

### Exemple tableau

| Méthode      | CPU (%) | RAM (MB) |
| ------------ | ------- | -------- |
| RestTemplate |         |          |
| Feign        |         |          |
| WebClient    |         |          |

## 🔟 Partie F — Résilience
### Scénarios

| Scénario                 | Observation attendue                                          |
| ------------------------ | ------------------------------------------------------------- |
| Panne VEHICLE-SERVICE    | Erreurs immédiates des clients, reprise après restart         |
| Panne Discovery          | Cache local possible, appels peuvent continuer temporairement |
| Redémarrage USER-SERVICE | Re-registration automatique dans discovery                    |

## 1️⃣1️⃣ Partie G — Analyse

Latence et débit :

Feign est plus lisible, facile à maintenir.

WebClient bloqué ≈ RestTemplate en performance.

Simplicité :

Feign > WebClient > RestTemplate

Impact Discovery :

Eureka vs Consul ≈ latence similaire en local.

Résilience :

Sans fallback, échec immédiat lors d’une panne.

Avec circuit breaker/fallback → tolérance.

## 1️⃣2️⃣ Livrables

Code complet USER-SERVICE et VEHICLE-SERVICE.

Captures Eureka & Consul montrant services enregistrés.

Résultats des tests de performance (latence / débit / CPU / RAM).

Analyse comparée (1–2 pages).

## 1️⃣3️⃣ Instructions pour exécution
VEHICLE-SERVICE
mvn spring-boot:run
# ou
java -jar target/vehicle-service.jar

USER-SERVICE
mvn spring-boot:run
# ou
java -jar target/user-service.jar

Test API
curl http://localhost:8082/api/users/1/vehicle/rest
curl http://localhost:8082/api/users/1/vehicle/feign
curl http://localhost:8082/api/users/1/vehicle/webclient

## 1️⃣4️⃣ Remarques finales

Délai artificiel côté VEHICLE-SERVICE : 20ms → comparer clients.

Pas de base de données : données en mémoire.

Tous les appels utilisent nom logique du service.

Eureka et Consul fonctionnent avec les mêmes endpoints.

## Creation

<img width="918" height="507" alt="Capture d&#39;écran 2026-01-12 040529" src="https://github.com/user-attachments/assets/4a2eb037-3e94-4ef6-b9af-afb5662863d2" />


<img width="904" height="496" alt="Capture d&#39;écran 2026-01-12 040656" src="https://github.com/user-attachments/assets/ae1a0942-ec4b-47fd-bc0b-4dac3a25bf42" />

<img width="247" height="349" alt="Capture d&#39;écran 2026-01-12 040924" src="https://github.com/user-attachments/assets/a1f0f724-fc74-4fcd-a9ca-1b2d8c6e3101" />

<img width="960" height="504" alt="Capture d&#39;écran 2026-01-12 040935" src="https://github.com/user-attachments/assets/4c25877a-cc86-4702-9e0d-350a96c8c9de" />



---

## Auteur

**Nom :** JARDI Siham

**Cours :** Architecture Microservices : Conception, Déploiement et Orchestration

**Date :** Janvier 2026

**Encadré par :** Pr.Mohamed LACHGAR


