package theuxzn16.com.github.diabetiq.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theuxzn16.com.github.diabetiq.dto.response.PerfilResponseDTO;
import theuxzn16.com.github.diabetiq.service.UsuarioService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilResponseDTO> findById(@PathVariable UUID id){
        PerfilResponseDTO perfil = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(perfil);
    }
}
