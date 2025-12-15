package cl.vasquez.nomadapp_backend.controller;

import cl.vasquez.nomadapp_backend.dto.ContactRequest;
import cl.vasquez.nomadapp_backend.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin
public class ContactController {

    private final EmailService emailService;

    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendContact(@RequestBody ContactRequest req) {
        emailService.sendContactMail(
                req.getNombre(),
                req.getCorreo(),
                req.getPais(),
                req.getMensaje()
        );
        return ResponseEntity.ok().build();
    }
}

