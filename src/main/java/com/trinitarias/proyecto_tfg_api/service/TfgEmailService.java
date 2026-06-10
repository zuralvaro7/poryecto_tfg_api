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

        System.out.println("1");

        SimpleMailMessage mensaje = new SimpleMailMessage();

        System.out.println("2");

        mensaje.setTo(to);

        System.out.println("3");

        mensaje.setFrom(System.getenv("USER_EMAIL"));

        System.out.println("4");

        mensaje.setSubject("Verificación de Cuenta");

        System.out.println("5");

        mensaje.setText("Texto");

        System.out.println("6");

        try {
            System.out.println("7");
            mailSender.send(mensaje);
            System.out.println("8");
        } catch (Exception e) {
            System.out.println("9");
            e.printStackTrace();
        }

        System.out.println("10");
    }

}
