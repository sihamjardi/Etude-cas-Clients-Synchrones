package ma.siham.cnfg;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration des clients HTTP utilisés par USER-SERVICE
 */
@Configuration
public class AppConfig {

    /**
     * RestTemplate synchrone classique
     * @LoadBalanced permet l'appel par nom logique
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Builder WebClient (réactif)
     * utilisé ici en mode bloquant pour le TP
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
