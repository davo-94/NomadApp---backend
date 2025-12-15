package cl.vasquez.nomadapp_backend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactMail(String nombre, String correo, String pais, String mensaje) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("bitacora.nomadapp@gmail.com");
        mail.setSubject("Nuevo mensaje de contacto");
        mail.setText(
                "Nombre: " + nombre +
                        "\nCorreo: " + correo +
                        "\nPaís: " + pais +
                        "\nMensaje:\n" + mensaje
        );

        mailSender.send(mail);
    }
}
