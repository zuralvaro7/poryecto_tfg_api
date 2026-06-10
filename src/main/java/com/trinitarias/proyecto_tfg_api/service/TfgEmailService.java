package com.trinitarias.proyecto_tfg_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class TfgEmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String from;


//    public void sendVerificationEmail(String to, String token) {
//        SimpleMailMessage mensaje = new SimpleMailMessage();
//        mensaje.setTo(to);
//        mensaje.setFrom(from);
//        mensaje.setSubject("Verificación de Cuenta");
//        mensaje.setText("Por favor, verifica tu cuenta: https://poryectotfgapi-production.up.railway.app/api/v1/tfg/verificar?token=" + token);
//        mailSender.send(mensaje);
//
//    }

    public void sendVerificationEmail(String to, String token) {

        System.out.println("=== INICIO ENVIO EMAIL ===");
        System.out.println("DESTINO: " + to);

        SimpleMailMessage mensaje = new SimpleMailMessage();

        mensaje.setTo(to);
        mensaje.setFrom("TU_CORREO_GMAIL@gmail.com");
        mensaje.setSubject("Verificación de Cuenta");

        mensaje.setText(
                "https://poryectotfgapi-production.up.railway.app/api/v1/tfg/verificar?token="
                        + token
        );

        try {
            mailSender.send(mensaje);
            System.out.println("EMAIL ENVIADO CORRECTAMENTE");
        } catch (Exception e) {
            System.out.println("ERROR ENVIANDO EMAIL");
            e.printStackTrace();
        }

        System.out.println("=== FIN ENVIO EMAIL ===");
    }

}
