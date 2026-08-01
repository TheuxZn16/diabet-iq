package theuxzn16.com.github.diabetiq.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioMedicoRequestDTO;
import theuxzn16.com.github.diabetiq.service.UsuarioMedicoService;

@RestController
@RequestMapping("/api/v1/usuario/medico")
public class UsuarioMedicoController {
    private final UsuarioMedicoService service;

    public UsuarioMedicoController(UsuarioMedicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioMedicoRequestDTO body){
        var response = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
