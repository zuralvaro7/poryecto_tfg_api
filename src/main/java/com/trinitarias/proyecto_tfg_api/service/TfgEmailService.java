package com.trinitarias.proyecto_tfg_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class TfgEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String token) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(to);
        mensaje.setFrom("norepli@tfgapi.com");
        mensaje.setSubject("Verificación de Cuenta");
        mensaje.setText("Por favor, verifica tu cuenta: http://localhost:8080/api/v1/tfg/verificar?token=" + token);
        mailSender.send(mensaje);

    }
}
