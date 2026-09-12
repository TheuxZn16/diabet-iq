package theuxzn16.com.github.diabetiq.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import theuxzn16.com.github.diabetiq.dto.response.PacienteReponseDTO;
import theuxzn16.com.github.diabetiq.dto.response.UsuarioResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioPacienteRequestDTO;
import theuxzn16.com.github.diabetiq.service.UsuarioPacienteService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuario/paciente")
public class UsuarioPacienteController {
    private final UsuarioPacienteService service;

    public UsuarioPacienteController(UsuarioPacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioPacienteRequestDTO body){
        var response = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<PacienteReponseDTO> update(@Valid @RequestBody UsuarioPacienteRequestDTO body, @PathVariable UUID id){
        var response = service.update(body, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
