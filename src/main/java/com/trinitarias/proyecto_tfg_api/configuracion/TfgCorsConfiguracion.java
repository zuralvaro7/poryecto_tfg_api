package com.trinitarias.proyecto_tfg_api.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class TfgCorsConfiguracion {

     @Bean
    public CorsConfigurationSource corsConfigurationSource(){
         CorsConfiguration conf = new CorsConfiguration();
         conf.setAllowedOriginPatterns(List.of("http://localhost:*", "https://proyecto-tfg-front-web.netlify.app/"));
         conf.setAllowedMethods(List.of("GET","POST", "PUT","DELETE"));
         conf.setAllowedHeaders(List.of("*"));
         conf.setAllowCredentials(true);

         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
         source.registerCorsConfiguration("/api/**", conf);
         return source;
     }
}
