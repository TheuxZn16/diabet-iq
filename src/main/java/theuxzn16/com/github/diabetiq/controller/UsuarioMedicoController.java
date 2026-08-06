package theuxzn16.com.github.diabetiq.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import theuxzn16.com.github.diabetiq.dto.response.MedicoResponseDTO;
import theuxzn16.com.github.diabetiq.dto.response.UsuarioResponseDTO;
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
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioMedicoRequestDTO body){
        var response = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<MedicoResponseDTO> update(@Valid @RequestBody UsuarioMedicoRequestDTO body){
        var response = service.update(body);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
