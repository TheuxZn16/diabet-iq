package theuxzn16.com.github.diabetiq.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theuxzn16.com.github.diabetiq.dto.response.UsuarioResponseDTO;
import theuxzn16.com.github.diabetiq.dto.resquest.UsuarioPacienteRequestDTO;
import theuxzn16.com.github.diabetiq.service.UsuarioPacienteService;

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
}
